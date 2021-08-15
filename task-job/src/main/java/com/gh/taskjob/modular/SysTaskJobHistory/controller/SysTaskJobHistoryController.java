package com.gh.taskjob.modular.SysTaskJobHistory.controller;


import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import com.gh.taskjob.modular.SysTaskJobHistory.model.TaskHistorySortModel;
import com.gh.taskjob.modular.SysTaskJobHistory.service.SysTaskJobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统-任务作业执行历史表 前端控制器
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/taskJob/sysTaskJobHistory")
public class SysTaskJobHistoryController {

    @Autowired
    private SysTaskJobHistoryService historyService;

    @PostMapping(value = "/list")
    public ResultData<List<SysTaskJobHistory>> list(@RequestBody PageFilter<SysTaskJobHistory> filter) {
        return historyService.list(filter);
    }

    @PostMapping(value = "/listSort")
    public ResultData<List<TaskHistorySortModel>> listSort(@RequestBody SysTaskJobHistory bo) {
        List<TaskHistorySortModel> sortModels = historyService.listSort(bo);
        return ResultData.success(sortModels);
    }

    @GetMapping(value = "/detail")
    public ResultData<SysTaskJobHistory> detail() {
        return ResultData.success();
    }
}

