package com.gh.consumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ManageController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/query")
    public String getEmpInfo(){
        String info = restTemplate.getForObject("http://provider/emp", String.class);
        return "消费者服务获取的信息: "+info;
    }
}
