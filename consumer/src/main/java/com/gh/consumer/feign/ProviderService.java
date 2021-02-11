package com.gh.consumer.feign;

import com.alibaba.fastjson.JSONObject;
import com.gh.common.toolsclass.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "provider")
public interface ProviderService {

    @RequestMapping(value = "/providerAPI")
    String getEmpInfo();

    @PostMapping(value = "/one/{id}")
    ResultData one(@PathVariable("id") String id);

    @PostMapping(value = "/all")
    ResultData all();

    @PostMapping(value = "/saveDemo")
    ResultData saveDemo(@RequestBody JSONObject json);
}
