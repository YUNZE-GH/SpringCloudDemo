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

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

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
    public ResultData start(String id) throws Exception {
        SysTaskJobPlan bo = baseMapper.selectById(id);
        if (bo == null) {
            throw new BusinessException("不存在该任务！");
        }
        if (bo.getStatus() == 1) {
            throw new BusinessException("当前任务已处于执行状态！");
        }

        getSysTaskJobPlanServiceImpl().executeTask(bo);

        return ResultData.success();
    }

    /**
     * 强制获取代理对象，必须开启exposeProxy配置，否则获取不到当前代理对象
     * @return SysTaskJobPlanServiceImpl
     */
    private SysTaskJobPlanServiceImpl getSysTaskJobPlanServiceImpl() {
        return AopContext.currentProxy() != null ? (SysTaskJobPlanServiceImpl) AopContext.currentProxy() : this;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void executeTask(SysTaskJobPlan bo) throws Exception {
        Integer taskPlanType = bo.getTaskPlanType();

        bo.setStatus(taskPlanType == 0 ? 0 : 1);    // 如果是执行一次，则不改变任务计划状态
        bo.setUpdateTime(LocalDateTime.now());
        bo.setUpdateUserId(null);
        this.updateById(bo);

        String classPath = bo.getTaskPlanExecuteClassPath();

//        Class<?> clazz = Class.forName(classPath);
//        Constructor<?>[] cons = clazz.getConstructors();
//        Runnable instance = (Runnable) cons[0].newInstance(bo.getTaskId());

        BaseTask instance = (BaseTask) applicationContext.getBean(classPath);
        instance.setParams(SDK.beanMapTool().beanToMap(bo));

        try {
            if (taskPlanType == 0) {
                // 执行一次
                instance.run();
            } else {
                // 循环执行
                this.executeScheduledFuture(instance, bo);
            }
        } catch (Exception e) {
            // 停止该定时任务
            futureMap.get(bo.getTaskId()).cancel(true);
            futureMap.remove(bo.getTaskId());
        }
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
        if (bo.getStatus() == 0) {
            throw new BusinessException("当前任务已处于停止状态！");
        }

        if (futureMap.containsKey(bo.getTaskId()) && futureMap.get(bo.getTaskId()) != null) {
            log.info("=====>    停止定时器:" + bo.getTaskId());
            futureMap.get(bo.getTaskId()).cancel(true);
            futureMap.remove(bo.getTaskId());

            bo.setUpdateTime(LocalDateTime.now());
            bo.setUpdateUserId(null);
            bo.setStatus(0);
            baseMapper.updateById(bo);
        }
        return ResultData.success();
    }

    @Override
    public void startedTask() throws Exception {
        LambdaQueryWrapper<SysTaskJobPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysTaskJobPlan::getStatus, 1).ne(SysTaskJobPlan::getTaskPlanType, 0);
        List<SysTaskJobPlan> list = baseMapper.selectList(lambdaQueryWrapper);
        for (SysTaskJobPlan bo : list) {
            this.executeTask(bo);
        }
    }
}
