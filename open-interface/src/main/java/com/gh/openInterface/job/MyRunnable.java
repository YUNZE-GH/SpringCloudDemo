package com.gh.openInterface.job;

import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/22 23:34
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.err.println("first quartz task:" + LocalDateTime.now());
    }
}
