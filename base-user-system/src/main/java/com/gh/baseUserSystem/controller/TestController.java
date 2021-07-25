package com.gh.baseUserSystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/22 23:56
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @PostMapping(value = "/a")
    public String a(){
        log.info("执行了Controller的方法a");
        return "a";
    }

    @PostMapping(value = "/b")
    public String b() {
        log.info("执行了Controller的方法b");
        return "b";
    }
}
