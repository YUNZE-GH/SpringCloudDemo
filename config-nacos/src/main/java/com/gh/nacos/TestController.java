package com.gh.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: blog-cloud
 * @description
 * @author: 3hgh
 * @create: 2020-12-23 15:27
 **/
@RefreshScope
@RestController
public class TestController {

    @Value("${name}")
    private String name;

    @RequestMapping(value = "/test")
    public String test(){
        return name;
    }
}
