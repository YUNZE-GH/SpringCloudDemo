package com.gh.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {

    @RequestMapping("/emp")
    public String getEmpInfo(){
        return "这是一个非常棒的同事";
    }
}
