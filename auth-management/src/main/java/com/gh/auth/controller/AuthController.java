package com.gh.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.gh.auth.config.AuthProperties;
import com.gh.auth.service.AuthService;
import com.gh.common.SDK;
import com.gh.common.toolsclass.ResultData;
import com.gh.common.toolsclass.UserJwt;
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

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResultData login(@RequestBody JSONObject json){
        // 校验数据

        // 登陆判断
        return authService.authVerify(json);
    }
}
