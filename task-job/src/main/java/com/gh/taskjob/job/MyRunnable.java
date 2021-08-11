package com.gh.taskjob.job;

import com.alibaba.fastjson.JSON;
import com.gh.common.toolsclass.BaseTask;
import com.gh.taskjob.annotation.HistoryLogAnnotation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/08/06 11:34
 */
@Service

public class MyRunnable extends BaseTask {

    @Override
    @HistoryLogAnnotation
    public void start(Map<String, ?> params) {
        LocalDateTime start = LocalDateTime.now();
        System.err.println("定时任务-开始:" + start);
        try {
            Thread.sleep(10000);
            LocalDateTime end = LocalDateTime.now();
            System.err.println("定时任务-结束:" + end);
            System.err.println("定时任务:" + start + "~" + end);
            System.err.println(JSON.toJSONString(params));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
