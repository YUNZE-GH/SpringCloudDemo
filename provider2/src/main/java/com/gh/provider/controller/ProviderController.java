package com.gh.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/providerAPI")
    public String getEmpInfo(){
        return serverPort + "的信息:这是一个非常棒的同事";
    }
}
