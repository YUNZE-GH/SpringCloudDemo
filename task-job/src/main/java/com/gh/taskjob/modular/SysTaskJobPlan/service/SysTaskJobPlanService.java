package com.gh.taskjob.modular.SysTaskJobPlan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * <p>
 * 系统-任务作业执行计划表 服务类
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
public interface SysTaskJobPlanService extends IService<SysTaskJobPlan> {

    /**
     * 获取正在执行的定时任务线程
     *
     * @return Map<String, ScheduledFuture>
     */
    Map<String, ScheduledFuture> getFutureMap();

    /**
     * 任务列表
     *
     * @param filter 过滤筛选条件
     * @return ResultData
     */
    ResultData list(PageFilter<SysTaskJobPlan> filter);

    /**
     * 新增定时任务
     *
     * @param bo SysTaskJobPlan 任务信息
     * @return ResultData
     */
    ResultData add(SysTaskJobPlan bo);

    /**
     * 编辑任务
     *
     * @param bo SysTaskJobPlan 任务信息
     * @return ResultData<SysTaskJobPlan>
     */
    ResultData<SysTaskJobPlan> update(SysTaskJobPlan bo);

    /**
     * 启动任务
     *
     * @param id 任务主键ID
     * @return ResultData
     */
    ResultData start(String id) throws Exception;

    /**
     * 停止任务
     *
     * @param id 任务主键ID
     * @return ResultData
     */
    ResultData stop(String id);

    /**
     * 执行任务
     *
     * @param bo SysTaskJobPlan 指定执行任务信息
     */
    void executeTask(SysTaskJobPlan bo) throws Exception;

    /**
     * 启动服务时所需要执行的任务
     *
     */
    void startedTask(List<SysTaskJobPlan> list);


}
