package com.gh.provider.modular.demo.controller;


import com.gh.common.toolsclass.ResultData;
import com.gh.common.enums.CodeEnum;
import com.gh.provider.modular.demo.entity.Demo;
import com.gh.provider.modular.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
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
@RefreshScope
@RestController
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private String serverPort;

    private final DemoService service;

    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }

    @PostMapping(value = "/one")
    public ResultData one() {
        logger.info("=====> /one");
        Demo byId = service.getById("1");
        return new ResultData(CodeEnum.SUCCESS.get(), byId, serverPort, LocalDateTime.now().toString());
    }

    @Value("${name}")
    private String name;

    @RequestMapping(value = "/test")
    public String test(){
        return name;
    }
}

