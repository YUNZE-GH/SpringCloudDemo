package com.gh.taskjob.modular.SysTaskJobPlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.common.SDK;
import com.gh.common.enums.CodeEnum;
import com.gh.common.exception.BusinessException;
import com.gh.taskjob.base.BaseTask;
import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;
import com.gh.taskjob.modular.SysTaskJobPlan.mapper.SysTaskJobPlanMapper;
import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import lombok.SneakyThrows;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
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

    private final Map<String, ScheduledFuture> futureMap = new HashMap<>();

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(9);
        return threadPoolTaskScheduler;
    }

    @Override
    public Map<String, ScheduledFuture> getFutureMap() {
        return this.futureMap;
    }

    @Override
    public ResultData list(PageFilter<SysTaskJobPlan> filter) {
        SysTaskJobPlan bo = filter.getData();

        LambdaQueryWrapper<SysTaskJobPlan> queryWrapper = new LambdaQueryWrapper<>();

        if (bo != null) {
            if (!StringUtils.isEmpty(bo.getTaskPlanType())) {
                queryWrapper.eq(SysTaskJobPlan::getTaskPlanType, bo.getTaskPlanType());
            }
            if (!StringUtils.isEmpty(bo.getTaskId())) {
                queryWrapper.eq(SysTaskJobPlan::getTaskId, bo.getTaskId());
            }
            if (!StringUtils.isEmpty(bo.getTaskName())) {
                queryWrapper.like(SysTaskJobPlan::getTaskName, bo.getTaskName());
            }
            if (!StringUtils.isEmpty(bo.getStatus())) {
                queryWrapper.eq(SysTaskJobPlan::getStatus, bo.getStatus());
            }
        }

        queryWrapper.orderByDesc(SysTaskJobPlan::getId);

        if (!StringUtils.isEmpty(filter.getLimit()) && filter.getLimit() == 0) {
            List<SysTaskJobPlan> list = baseMapper.selectList(queryWrapper);
            return new ResultData(CodeEnum.SUCCESS.get(), list.size(), list, "查询成功！");
        }

        IPage<SysTaskJobPlan> iPage = new Page<>(filter.getPage(), filter.getLimit());
        iPage = baseMapper.selectPage(iPage, queryWrapper);

        return new ResultData(CodeEnum.SUCCESS.get(), (int) iPage.getTotal(), iPage.getRecords(), "查询成功！");
    }

    @Override
    public ResultData add(SysTaskJobPlan bo) {
        bo.setTaskId(UUID.randomUUID().toString());
        bo.setCreateUserId(null);

        if (!StringUtils.isEmpty(bo.getTaskPlanCron())) {
            // 校验该Cron通配符是否可以生成Cron触发器
            new CronTrigger(bo.getTaskPlanCron());
        }

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

        if (2 == bo.getTaskPlanType() && !StringUtils.isEmpty(bo.getTaskPlanCron())) {
            // 校验该Cron通配符是否可以生成Cron触发器
            new CronTrigger(bo.getTaskPlanCron());
        }

        int i = baseMapper.updateById(bo);
        if (i > 0) {
            return ResultData.success();
        }
        return ResultData.error("操作失败！");
    }

    @Override
    public ResultData start(String id) throws Exception {
        SysTaskJobPlan bo = baseMapper.selectById(id);
        if (bo == null) {
            throw new BusinessException("不存在该任务！");
        }
        if (bo.getStatus() == 1 && getFuture(bo.getTaskId())) {
            throw new BusinessException("当前任务已处于执行状态！");
        }

        // 如果任务已经在执行了，则关闭后再重新启动
        if (getFuture(bo.getTaskId())) {
            futureMap.get(bo.getTaskId()).cancel(true);
            futureMap.remove(bo.getTaskId());
            getSysTaskJobPlanServiceImpl().executeTask(bo);
            return new ResultData(CodeEnum.SUCCESS.get(), null, "该任务已处于执行状态，已重新启动");
        }

        getSysTaskJobPlanServiceImpl().executeTask(bo);

        return ResultData.success();
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
     * 开启注解服务重新启动时将所有任务数据都修改为停止状态
     *
     * @throws Exception
     */
    //   @PostConstruct
    public void initialized() throws Exception {
        SysTaskJobPlan sysTaskJobPlan = new SysTaskJobPlan();
        sysTaskJobPlan.setStatus(0);
        try {
            baseMapper.update(sysTaskJobPlan, Wrappers.<SysTaskJobPlan>lambdaQuery().eq(SysTaskJobPlan::getStatus, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void executeTask(SysTaskJobPlan bo) throws Exception {
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

        try {
            if (taskPlanType == 0) {
                // 执行一次
                instance.run();
            } else {
                // 循环执行
                this.executeScheduledFuture(instance, bo);
            }
            this.updateById(bo);
        } catch (Exception e) {

            // 出现异常，停止定时任务
           /* if (taskPlanType != 0) {
                // 停止该定时任务
                if (futureMap.containsKey(bo.getTaskId())) {
                    futureMap.get(bo.getTaskId()).cancel(true);
                    futureMap.remove(bo.getTaskId());
                }
                CommonUtil.breaks(StringUtils.isEmpty(e.getMessage()) ? "定时任务执行失败" : e.getMessage().replace("提示：", ""));
            }*/
        }
    }

    // 执行线程任务
    public void executeScheduledFuture(Runnable instance, SysTaskJobPlan bo) throws Exception {
        try {
            // 循环执行
            ScheduledFuture future = threadPoolTaskScheduler.schedule(instance, new Trigger() {
                @SneakyThrows
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext) {
                    try {
                        if (bo.getTaskPlanType() == 2) {
                            // 根据 cron 表达式执行
                            return new CronTrigger(bo.getTaskPlanCron()).nextExecutionTime(triggerContext);
                        } else {
                            // 无限次循环执行
                            return new PeriodicTrigger(bo.getTaskPlanFixedRate()).nextExecutionTime(triggerContext);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();

                        // 定时循环执行任务启动执行失败
                        throw new BusinessException("定时任务启动执行失败: " + e.getMessage());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        throw new BusinessException("定时任务启动执行失败");
                    }
                }
            });
            futureMap.put(bo.getTaskId(), future);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException((e.getMessage().replace("提示：", "")));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData stop(String id) {
        SysTaskJobPlan bo = baseMapper.selectById(id);
        if (getFuture(bo.getTaskId())) {
            log.info("=====>    停止定时器:" + bo.getTaskId());
            futureMap.get(bo.getTaskId()).cancel(true); //关闭线程
            futureMap.remove(bo.getTaskId());        // 从集合中移除

            if (bo == null) {
                throw new BusinessException("已停止任务，但数据库不存在该任务信息！");
            }
            if (bo.getStatus() == 0) {
                throw new BusinessException("已停止任务，但数据库中当前任务已处于停止状态！");
            }
        }

        if (bo == null) {
            throw new BusinessException("不存在该任务！");
        }
        if (bo.getStatus() == 0) {
            throw new BusinessException("当前任务已处于停止状态！");
        }

        bo.setUpdateTime(LocalDateTime.now());
        bo.setUpdateUserId(null);
        bo.setStatus(0);
        baseMapper.updateById(bo);
        return ResultData.success();
    }

    // 查询线程集合中是否存在该线程
    private Boolean getFuture(String taskIds) {
        return futureMap.containsKey(taskIds) && futureMap.get(taskIds) != null;
    }

    // 服务重启时，将数据库中需要启动的定时任务启动
    @PostConstruct
    public void initStartedTask() {
        LambdaQueryWrapper<SysTaskJobPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysTaskJobPlan::getStatus, 1).ne(SysTaskJobPlan::getTaskPlanType, 0).eq(SysTaskJobPlan::getInvalid, 0);
        List<SysTaskJobPlan> list = baseMapper.selectList(lambdaQueryWrapper);
        this.startedTask(list);
    }

    // 批量启动定时任务
    @Override
    public void startedTask(List<SysTaskJobPlan> list) {
        if (list.size() > 0) {
            int i = 0;
            boolean haveFail = false;

            for (SysTaskJobPlan bo : list) {
                try {
                    this.executeTask(bo);
                } catch (Exception e) {
                    haveFail = true;

                    if (i == 0) {
                        log.error("=====> 服务启动时，启动定时任务失败列表如下 【start】");
                    }

                    log.error("任务TaskId:" + bo.getTaskId() + " 任务名称:" + bo.getTaskName());
                } finally {
                    i++;

                    if (list.size() == i && haveFail) {
                        log.error("=====> 服务启动时，启动定时任务失败列表如上 【end】");
                    }
                }
            }
        }

    }
}
