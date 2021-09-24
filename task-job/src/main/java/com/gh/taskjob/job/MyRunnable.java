package com.gh.taskjob.job;

import com.gh.common.toolsclass.BaseTask;
import com.gh.taskjob.annotation.HistoryLogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/08/06 11:34
 */
@Service
@Slf4j
public class MyRunnable extends BaseTask {

    @Override
    @HistoryLogAnnotation
    public void start(Map<String, Object> params) {
        LocalDateTime start = LocalDateTime.now();
        try {
//            Thread.sleep(10000);
            LocalDateTime end = LocalDateTime.now();
            log.info("=====>    定时任务:" + start + " ~ " + end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
