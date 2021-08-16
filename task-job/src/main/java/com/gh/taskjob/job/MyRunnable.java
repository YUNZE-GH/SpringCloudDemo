package com.gh.taskjob.job;

import com.alibaba.fastjson.JSON;
import com.gh.common.toolsclass.BaseTask;
import com.gh.taskjob.annotation.HistoryLogAnnotation;
import org.springframework.scheduling.annotation.Async;
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
    @Async
    @HistoryLogAnnotation
    public void start(Map<String, ?> params) {
        LocalDateTime start = LocalDateTime.now();
        try {
            Thread.sleep(10000);
            LocalDateTime end = LocalDateTime.now();
            System.err.println("定时任务:" + start + " ~ " + end);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
