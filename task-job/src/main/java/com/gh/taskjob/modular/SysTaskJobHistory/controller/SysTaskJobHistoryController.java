package com.gh.taskjob.modular.SysTaskJobHistory.controller;


import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/list")
    public ResultData<List<SysTaskJobHistory>> list() {
        return ResultData.success();
    }

    @GetMapping(value = "/detail")
    public ResultData<SysTaskJobHistory> detail() {
        return ResultData.success();
    }
}

