package com.gh.consumer;

import com.gh.common.toolsclass.ResultData;
import com.gh.redis.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsumerApplicationTests {

    final RedisUtil redisUtil;

    @Autowired
    public ConsumerApplicationTests(RedisUtil redisUtil){
        this.redisUtil = redisUtil;
    }

    @Test
    void test1() {
        // 如果存在demo缓存，就删除
        if (redisUtil.hasKey("demo")) {
            System.err.println(redisUtil.remove("demo"));
        }
        // 插入新的demo缓存
        ResultData resultData = new ResultData();
        resultData.setCode(0);
        resultData.setMessage("redis测试-2");
        resultData.setData("888888");
        redisUtil.insertOrUpdate("demo", resultData);
        Object demo = redisUtil.get("demo");
        ResultData bo = (ResultData) demo;
        System.err.println(bo.toString());
    }

    @Test
    void test2() {
        redisUtil.insertOrUpdate("test", "redis工具测试");
        System.err.println(redisUtil.get("test"));
    }

}
