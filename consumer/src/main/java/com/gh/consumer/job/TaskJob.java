package com.gh.consumer.job;

import com.alibaba.fastjson.JSON;
import com.gh.common.SDK;
import com.gh.common.toolsclass.ResultData;
import com.gh.consumer.websocket.WebSocket;
import com.gh.redis.util.RedisUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author gaohan
 * @version 1.0
 * @date 2021-03-04 08:44
 * Scheduled定时任务
 */
@Component
public class TaskJob {
    final RedisUtil redisUtil;

    @Autowired
    public TaskJob(RedisUtil redisUtil){
        this.redisUtil = redisUtil;
    }

    /**
     * 定时发送系统通知信息
     */
    //    @Scheduled(cron = "/5 * * * ?")
    @Scheduled(fixedRate = 1000 * 10)
    public void jobInfo() {
        System.err.println("===========> 定时任务执行" + SDK.getDateUtils().getDateTime());
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("messageType", 5);
        map1.put("textMessage", SDK.getDateUtils().getDateTime() + " 系统定时信息通知！");
        map1.put("fromusername", "admin");
        map1.put("tousername", "所有人");
        for (WebSocket item : WebSocket.getClients().values()) {
            item.getSession().getAsyncRemote().sendText(JSON.toJSONString(map1));
        }

        System.err.println(((ResultData)redisUtil.get("demo")).toString());
    }
}
