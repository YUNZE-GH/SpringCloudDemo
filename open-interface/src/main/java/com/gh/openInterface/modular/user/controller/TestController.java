package com.gh.openInterface.modular.user.controller;

import com.gh.openInterface.modular.test.AuthFilter;
import com.gh.openInterface.modular.test.LoggerAnnotation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * blog-cloud
 *
 * @author 3hgh
 * @version 1.0
 * @date 2021-06-09 14:59
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @AuthFilter(value = "/a")
    @PostMapping(value = "/a")
    public String login() {
        return "ok!";
    }


    @LoggerAnnotation
    @PostMapping(value = "/b")
    public String b() {
        return "b";
    }
}
