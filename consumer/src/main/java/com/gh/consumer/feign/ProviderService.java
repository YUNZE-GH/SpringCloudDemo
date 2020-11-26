package com.gh.consumer.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "provider")
public interface ProviderService {

    @RequestMapping(value = "/providerAPI")
    String getEmpInfo();
}
