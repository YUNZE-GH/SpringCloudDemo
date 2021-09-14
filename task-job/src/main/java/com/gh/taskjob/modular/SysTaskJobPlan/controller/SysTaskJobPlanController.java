package com.gh.taskjob.modular.SysTaskJobPlan.controller;


import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.redis.util.RedisUtil;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;
import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Predicate;

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
@Slf4j
public class SysTaskJobPlanController {

    @Autowired
    private SysTaskJobPlanService taskJobPlanService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping(value = "/list")
    public ResultData<List<SysTaskJobPlan>> list(@RequestBody PageFilter<SysTaskJobPlan> filter) {
        log.info("开始加锁==>" + Thread.currentThread().getId());
        boolean b = redisUtil.lock("LOCK_TASK_JOB", filter.getPage().toString());

        try {
            if (b) {
                log.info("开始执行业务==>" + Thread.currentThread().getId());
                Thread.sleep(20000);
                log.info("结束执行业务==>" + Thread.currentThread().getId());

                // 删除锁；
                log.info("开始解锁==>" + Thread.currentThread().getId());
                redisUtil.unlock("LOCK_TASK_JOB", filter.getPage().toString());
            } else {
                log.info("获取锁失败==>" + Thread.currentThread().getId());
                return ResultData.error("获取锁失败");
            }
        } catch (Exception e) {
            log.info("获取锁异常{}", e);
            // 删除锁；
            log.info("开始解锁==>" + Thread.currentThread().getId());
            redisUtil.unlock("LOCK_TASK_JOB", filter.getPage().toString());
        }
        return taskJobPlanService.list(filter);
    }

    @GetMapping(value = "/detail/{id}")
    public ResultData<SysTaskJobPlan> detail(@PathVariable("id") String id) {
        return ResultData.success(taskJobPlanService.getById(id));
    }

    @PostMapping(value = "/add")
    public ResultData add(@RequestBody SysTaskJobPlan bo) {
        return taskJobPlanService.add(bo);
    }

    @PostMapping(value = "/update")
    public ResultData<SysTaskJobPlan> update(@RequestBody SysTaskJobPlan bo) {
        return taskJobPlanService.update(bo);
    }

    @GetMapping(value = "/delete/{id}")
    public ResultData delete(@PathVariable(value = "id") Integer id) {
        taskJobPlanService.removeById(id);
        return ResultData.success();
    }

    @PostMapping(value = "/start/{id}")
    public ResultData start(@PathVariable("id") String id) throws Exception {
        return taskJobPlanService.start(id);
    }

    @PostMapping(value = "/stop/{id}")
    public ResultData stop(@PathVariable("id") String id) {
        return taskJobPlanService.stop(id);
    }

    @PostMapping(value = "/queryThreadPoolTask")
    public ResultData<Map<String, ScheduledFuture>> queryThreadPoolTask() {
        return ResultData.success();
    }

}

