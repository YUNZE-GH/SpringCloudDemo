package com.gh.baseUserSystem.controller;

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
public class TestController {

    @PostMapping(value = "/a")
    public String a(){
        return "a";
    }
}
