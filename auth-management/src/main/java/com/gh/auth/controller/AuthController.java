package com.gh.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/22 23:49
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @PostMapping(value = "/login")
    public String login(){
        return "OK";
    }
}
