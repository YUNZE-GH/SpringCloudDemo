package com.gh.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.gh.auth.config.AuthProperties;
import com.gh.common.SDK;
import com.gh.common.service.impl.HttpUtilsImpl;
import com.gh.common.toolsclass.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /*@Autowired
    private HttpUtilsImpl httpUtils;*/

    @Autowired
    private AuthProperties authProperties;

    @PostMapping(value = "/login")
    public String login(@RequestBody JSONObject json){
        return SDK.httpUtils().doPostJson(authProperties.getServerPath() + authProperties.getAuthVerifyPath(), json.toString());
    }
}
