package com.gh.consumer.controller;


import com.gh.consumer.feign.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Resource
    ProviderService service;

    @RequestMapping("/query")
    public String getEmpInfo(){
//        String info = restTemplate.getForObject("http://provider/providerAPI", String.class);
        String info = service.getEmpInfo();
        return "消费者服务获取 " + info;
    }
}
