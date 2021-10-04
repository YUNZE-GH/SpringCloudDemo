package com.gh.taskjob.modular.SysTaskJobPlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.common.SDK;
import com.gh.common.enums.CodeEnum;
import com.gh.common.exception.BusinessException;
import com.gh.common.toolsclass.BaseTask;
import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.redis.util.RedisUtil;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;
import com.gh.taskjob.modular.SysTaskJobPlan.mapper.SysTaskJobPlanMapper;
import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * <p>
 * 系统-任务作业执行计划表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
@Service
@Slf4j
public class SysTaskJobPlanServiceImpl extends ServiceImpl<SysTaskJobPlanMapper, SysTaskJobPlan> implements SysTaskJobPlanService {

    private final String LOCK_LABEL = "LOCK_TASK_JOB";

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 用于存放正在执行的定时任务
     */
    private Map<String, ScheduledFuture> futureMap = new HashMap<>();

    @Override
    public ResultData<List<SysTaskJobPlan>> list(PageFilter<SysTaskJobPlan> filter) {
        SysTaskJobPlan bo = filter.getData();
        LambdaQueryWrapper<SysTaskJobPlan> queryWrapper = new LambdaQueryWrapper<>();
        if (bo != null) {
            if (!StringUtils.isEmpty(bo.getTaskPlanType())) {
                queryWrapper.eq(SysTaskJobPlan::getTaskPlanType, bo.getTaskPlanType());
            }
            if (!StringUtils.isEmpty(bo.getTaskName())) {
                queryWrapper.like(SysTaskJobPlan::getTaskName, bo.getTaskName());
            }
            if (!StringUtils.isEmpty(bo.getStatus())) {
                queryWrapper.eq(SysTaskJobPlan::getStatus, bo.getStatus());
            }
            if (!StringUtils.isEmpty(filter.getStartTime())) {
                queryWrapper.gt(SysTaskJobPlan::getCreateTime, filter.getStartTime());
            }
            if (!StringUtils.isEmpty(filter.getEndTime())) {
                queryWrapper.lt(SysTaskJobPlan::getCreateTime, filter.getEndTime());
            }
        }
        queryWrapper.orderByDesc(SysTaskJobPlan::getId);

        if (!StringUtils.isEmpty(filter.getLimit()) && filter.getLimit() == 0) {
            List<SysTaskJobPlan> list = baseMapper.selectList(queryWrapper);
            return new ResultData<>(CodeEnum.SUCCESS.get(), list.size(), list, "查询成功！");
        }

        IPage<SysTaskJobPlan> iPage = new Page<>(filter.getPage(), filter.getLimit());
        iPage = baseMapper.selectPage(iPage, queryWrapper);
        List<SysTaskJobPlan> list = iPage.getRecords();
        return new ResultData<>(CodeEnum.SUCCESS.get(), (int) iPage.getTotal(), list, "查询成功！");
    }

    @Override
    public ResultData add(SysTaskJobPlan bo) {
        bo.setTaskId(UUID.randomUUID().toString());
        bo.setCreateUserId(null);
        int insert = baseMapper.insert(bo);
        return insert > 0 ? ResultData.success() : ResultData.error("新增失败！");
    }

    @Override
    public ResultData<SysTaskJobPlan> update(SysTaskJobPlan bo) {
        SysTaskJobPlan plan = baseMapper.selectById(bo.getId());
        if (plan == null) {
            throw new BusinessException("不存在该任务！");
        }
        if (plan.getStatus() == 1) {
            throw new BusinessException("当前任务处于执行状态,不可编辑任务信息！");
        }
        bo.setUpdateTime(LocalDateTime.now());
        bo.setUpdateUserId(null);
        int i = baseMapper.updateById(bo);
        if (i > 0) {
            return ResultData.success();
        }
        return ResultData.error("操作失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData start(String id) {
        SysTaskJobPlan bo = baseMapper.selectById(id);
        if (bo == null) {
            throw new BusinessException("不存在该任务！");
        }

        // 如果该任务已经处于启动执行状态,则先关闭任务，然后再次重新启动任务
        if (futureMap.containsKey(bo.getTaskId()) && futureMap.get(bo.getTaskId()) != null) {

            if (1 == bo.getStatus()) {
                throw new BusinessException("当前任务已处于执行状态！");
            } else {
                // 关闭
                futureMap.get(bo.getTaskId()).cancel(false);
                futureMap.remove(bo.getTaskId());

                // 启动
                return getSysTaskJobPlanServiceImpl().executeTask(bo);
            }

        }

        return getSysTaskJobPlanServiceImpl().executeTask(bo);
    }

    /**
     * 强制获取代理对象，必须开启exposeProxy配置，否则获取不到当前代理对象
     *
     * @return SysTaskJobPlanServiceImpl
     */
    private SysTaskJobPlanServiceImpl getSysTaskJobPlanServiceImpl() {
        return AopContext.currentProxy() != null ? (SysTaskJobPlanServiceImpl) AopContext.currentProxy() : this;
    }


    /**
     * 执行调度任务
     *
     * @param bo 任务详细信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData executeTask(SysTaskJobPlan bo) {
        Integer taskPlanType = bo.getTaskPlanType();

        bo.setStatus(taskPlanType == 0 ? 0 : 1);    // 如果是执行一次，则不改变任务计划状态
        bo.setUpdateTime(LocalDateTime.now());
        bo.setUpdateUserId(null);

        String className = bo.getTaskPlanExecuteClassName();

//        Class<?> clazz = Class.forName(classPath);
//        Constructor<?>[] cons = clazz.getConstructors();
//        Runnable instance = (Runnable) cons[0].newInstance(bo.getTaskId());

        BaseTask instance = (BaseTask) applicationContext.getBean(className);
        instance.setParams(SDK.beanMapTool().beanToMap(bo));

        boolean lockMark = false;   // 加锁成功标记

        try {
            log.info("开始加锁==> 任务ID：" + bo.getTaskId() + " 线程ID：" + Thread.currentThread().getId());

            // 加锁
            lockMark = redisUtil.lock(LOCK_LABEL, bo.getTaskId());

            if (lockMark) {

                log.info("加锁成功==> 任务ID：" + bo.getTaskId() + " 线程ID：" + Thread.currentThread().getId());

                Thread.sleep(200000);

                // 加锁成功，开始执行定时任务
                if (taskPlanType == 0) {
                    // 执行一次
                    instance.run();
                } else {
                    // 循环执行
                    this.executeScheduledFuture(instance, bo);
                }

                // 解锁
                log.info("开始解锁==> 任务ID：" + bo.getTaskId() + " 线程ID：" + Thread.currentThread().getId());
                redisUtil.unlock(LOCK_LABEL, bo.getTaskId());
            } else {
                log.info("获取锁失败==> 任务ID：" + bo.getTaskId() + " 线程ID：" + Thread.currentThread().getId());
            }
        } catch (Exception e) {
            if (lockMark) {

                // 加锁成功，但是任务执行失败了，停止该定时任务，并解锁
                // 如果是执行一次，则不需要停止任务
                if (taskPlanType == 0) {
                    futureMap.get(bo.getTaskId()).cancel(true);
                    futureMap.remove(bo.getTaskId());
                }

                // 解锁
                redisUtil.unlock(LOCK_LABEL, bo.getTaskId());
            } else {
                // 加锁失败，返回提示，无需停止该任务和该任务加的锁
                throw new BusinessException("定时任务执行失败");
            }
        } finally {
            getSysTaskJobPlanServiceImpl().updateById(bo);
        }
        return ResultData.success();
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void executeScheduledFuture(Runnable instance, SysTaskJobPlan bo) {
        // 循环执行
        ScheduledFuture future = threadPoolTaskScheduler.schedule(instance, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                if (bo.getTaskPlanType() == 2) {
                    return new CronTrigger(bo.getTaskPlanCron()).nextExecutionTime(triggerContext);
                } else {
                    return new PeriodicTrigger(bo.getTaskPlanFixedRate()).nextExecutionTime(triggerContext);
                }
            }
        });
        futureMap.put(bo.getTaskId(), future);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData stop(String id) {
        SysTaskJobPlan bo = baseMapper.selectById(id);

        if (bo == null) {
            throw new BusinessException("不存在该任务！");
        }

        if (futureMap.containsKey(bo.getTaskId()) && futureMap.get(bo.getTaskId()) != null) {

            futureMap.get(bo.getTaskId()).cancel(false);
            futureMap.remove(bo.getTaskId());

            if (bo.getStatus() == 0) {
                throw new BusinessException("当前任务已处于停止状态！");
            }

            bo.setUpdateTime(LocalDateTime.now());
            bo.setUpdateUserId(null);
            bo.setStatus(0);
            baseMapper.updateById(bo);
        }
        return ResultData.success();
    }

    @Override
    public void startedTask() {
        LambdaQueryWrapper<SysTaskJobPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysTaskJobPlan::getStatus, 1).ne(SysTaskJobPlan::getTaskPlanType, 0);
        List<SysTaskJobPlan> list = baseMapper.selectList(lambdaQueryWrapper);
        for (SysTaskJobPlan bo : list) {
            this.executeTask(bo);
        }
    }
}
