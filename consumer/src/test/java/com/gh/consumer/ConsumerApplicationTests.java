package com.gh.consumer;

import com.gh.common.toolsclass.ResultData;
import com.gh.redis.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;

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

    @Test
    public void test() {
        //利用函数式接口Consumer的accept方法实现打印，Lambda表达式如下
        Consumer<Object> consumer = this::println;
        consumer.accept("jay");
        printlnJay(consumer);
    }

    private void printlnJay(Consumer<Object> consumer) {
        consumer.accept(11);
    }

    private void println(Object msg) {
        System.out.println(msg.toString());
    }
}
