package com.gh.consumer.feign;

import com.gh.common.toolsclass.ResultData;
import com.gh.consumer.config.SentinelHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider", fallback = SentinelHandler.class)
public interface ProviderService {

    @RequestMapping(value = "/providerAPI")
    String getEmpInfo();

    @PostMapping(value = "/one/{id}")
    ResultData one(@PathVariable("id") String id);

    @PostMapping(value = "/all")
    ResultData all();
}
