package com.gh.taskjob.modular.SysTaskJobPlan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * <p>
 * 系统-任务作业执行计划表 服务类
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
public interface SysTaskJobPlanService extends IService<SysTaskJobPlan> {

    ResultData<List<SysTaskJobPlan>> list(PageFilter<SysTaskJobPlan> filter);

    ResultData add(SysTaskJobPlan bo);

    ResultData<SysTaskJobPlan> update(SysTaskJobPlan bo);

    ResultData start(String id) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException;

    ResultData stop(String id);
}
