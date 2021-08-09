package com.gh.taskjob.job;

import com.alibaba.fastjson.JSON;
import com.gh.common.toolsclass.BaseTask;
import com.gh.taskjob.annotation.HistoryLogAnnotation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/08/06 11:34
 */
@Service
public class MyRunnable extends BaseTask {

    @Override
    @HistoryLogAnnotation
    public void run() {
        System.err.println("定时任务:" + LocalDateTime.now());
        System.err.println(JSON.toJSONString(super.getParams()));
    }
}
