package com.gh.consumer.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gh.consumer.config.SentinelBlockHandler;
import org.springframework.stereotype.Service;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/2/10 17:03
 */
@Service
public class TestService {

    @SentinelResource(value = "error", fallback = "getNameFallBack")
    public String getUserName(int i) throws Exception {
        if (i % 3 != 0) throw new Exception();
        return "请求成功";
    }

    public String getNameFallBack(int i){
        return "-fallback" + i;
    }
}
