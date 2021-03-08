package com.gh.provider.modular.user.controller;


import com.gh.provider.modular.user.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.soap.Addressing;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/user/baseUser")
public class BaseUserController {

    final BaseUserService service;

    @Autowired
    public BaseUserController(BaseUserService service){
        this.service = service;
    }

    @GetMapping(value = "/getCount")
    public int getCount(){
        return service.getCount();
    }
}

