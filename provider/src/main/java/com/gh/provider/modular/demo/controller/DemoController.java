package com.gh.provider.modular.demo.controller;


import com.gh.common.ResultData;
import com.gh.common.enums.CodeEnum;
import com.gh.provider.modular.demo.entity.Demo;
import com.gh.provider.modular.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RestController
public class DemoController {

    private final DemoService service;

    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }

    @RequestMapping(value = "/one")
    public ResultData one() {
        Demo byId = service.getById("1");
        return new ResultData(CodeEnum.SUCCESS.get(), byId, LocalDateTime.now().toString(), LocalDateTime.now().toString());
    }
}

