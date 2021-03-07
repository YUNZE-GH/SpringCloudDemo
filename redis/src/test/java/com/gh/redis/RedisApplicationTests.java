package com.gh.redis;

import com.gh.common.toolsclass.ResultData;
import com.gh.redis.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作key-value都是字符串，最常用

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test1() {
        ResultData resultData = new ResultData();
        resultData.setCode(0);
        resultData.setMessage("redis测试");
        resultData.setData("666666");
        redisUtil.insertOrUpdate("demo", resultData);
        System.err.println(redisUtil.hasKey("demo"));
        Object demo = redisUtil.get("demo");
        ResultData bo = (ResultData) demo;
        System.err.println(bo.toString());
    }

    @Test
    void contextLoads() {
        // 字符串操作
        stringRedisTemplate.opsForValue().append("msg", "coder");

        // 列表操作
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

    @Test
    void test2() {
        Set<String> list = redisUtil.getPattern("d");
        for (String s: list) {
            System.err.println(s);
        }
    }

}
