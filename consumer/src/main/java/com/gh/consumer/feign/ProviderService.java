package com.gh.consumer.feign;

import com.gh.common.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "provider")
public interface ProviderService {

    @RequestMapping(value = "/providerAPI")
    String getEmpInfo();

    @RequestMapping(value = "/one")
    ResultData one();
}
