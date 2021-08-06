package com.gh.taskjob.job;

import com.gh.taskjob.annotation.HistoryLogAnnotation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/22 23:34
 */
@Component
public class MyRunnable implements Runnable {

    @Override
    @HistoryLogAnnotation
    public void run() {
        System.err.println("定时任务run！");
        System.err.println("定时任务:" + LocalDateTime.now());
    }
}
