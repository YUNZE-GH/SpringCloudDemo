package com.gh.taskjob.modular.SysTaskJobPlan.controller;


import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;
import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * <p>
 * 系统-任务作业执行计划表 前端控制器
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/taskJob/sysTaskJobPlan")
public class SysTaskJobPlanController {

    @Autowired
    private SysTaskJobPlanService taskJobPlanService;

    @PostMapping(value = "/list")
    public ResultData<List<SysTaskJobPlan>> list(PageFilter<SysTaskJobPlan> filter) {
        return taskJobPlanService.list(filter);
    }

    @PostMapping(value = "/add")
    public ResultData<SysTaskJobPlan> add(SysTaskJobPlan bo) {
        return taskJobPlanService.add(bo);
    }

    @PostMapping(value = "/update")
    public ResultData<SysTaskJobPlan> update() {
        return ResultData.success();
    }

    @GetMapping(value = "/delete/{id}")
    public ResultData delete(@PathVariable(value = "id") Integer id) {
        taskJobPlanService.removeById(id);
        return ResultData.success();
    }

    @PostMapping(value = "/start")
    public ResultData<SysTaskJobPlan> start() {
        return ResultData.success();
    }

    @PostMapping(value = "/end")
    public ResultData<SysTaskJobPlan> end() {
        return ResultData.success();
    }

    @PostMapping(value = "/queryThreadPoolTask")
    public ResultData<Map<String, ScheduledFuture>> queryThreadPoolTask(){
        return ResultData.success();
    }

}

