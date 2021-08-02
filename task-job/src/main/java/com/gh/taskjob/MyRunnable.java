package com.gh.taskjob;

import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/22 23:34
 */
public class MyRunnable implements Runnable{

    private String uuid;

    public MyRunnable(String uuid) {
        this.uuid = uuid;
    }
    @Override
    public void run() {
        System.err.println("定时任务" + this.uuid + ":" + LocalDateTime.now());
    }
}
