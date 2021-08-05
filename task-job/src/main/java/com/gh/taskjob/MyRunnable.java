package com.gh.taskjob;

import com.gh.taskjob.annotation.HistoryLogAnnotation;

import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/22 23:34
 */
public class MyRunnable implements Runnable{


    @Override
    @HistoryLogAnnotation
    public void run() {
        System.err.println("定时任务:" + LocalDateTime.now());
    }
}
