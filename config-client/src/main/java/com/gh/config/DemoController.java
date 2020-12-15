package com.gh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaohan
 * @version 1.0
 * @date 2020/12/15 22:42
 */
@RestController
public class DemoController {
    @Value("${foo}")
    private String foo;

    @RequestMapping(value = "get")
    public String get() {
        return foo;
    }
}
