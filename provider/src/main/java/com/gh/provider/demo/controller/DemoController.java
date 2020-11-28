package com.gh.provider.demo.controller;


import com.gh.provider.demo.entity.Demo;
import com.gh.provider.demo.service.DemoService;
import com.gh.provider.utils.CodeEnum;
import com.gh.provider.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 测试表 前端控制器
 * </p>
 *
 * @author gaohan
 * @since 2020-11-26
 */
@RestController(value = "demo")
public class DemoController {

    private final DemoService service;

    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }

    @GetMapping(value = "all")
    public Result all() {
        Demo byId = service.getById("1");
        return new Result(CodeEnum.SUCCESS.get(), byId, LocalDateTime.now().toString());
    }
}

