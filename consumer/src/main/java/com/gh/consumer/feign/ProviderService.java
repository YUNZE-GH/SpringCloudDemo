package com.gh.consumer.feign;

import com.gh.common.toolsclass.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider")
public interface ProviderService {

    @RequestMapping(value = "/providerAPI")
    String getEmpInfo();

    @PostMapping(value = "/one")
    ResultData one();
}
