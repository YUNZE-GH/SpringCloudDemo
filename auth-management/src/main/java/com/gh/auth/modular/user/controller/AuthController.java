package com.gh.auth.modular.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.gh.auth.modular.user.service.BaseUserService;
import com.gh.common.toolsclass.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private BaseUserService baseUserService;

    @PostMapping(value = "/login")
    public ResultData<Object> login(@RequestBody JSONObject json) throws Exception {
        if (!json.containsKey("userAccount") || !json.containsKey("userPassword")) {
            throw new Exception("登录异常，账号和密码不能为空");
        }
        String token = baseUserService.loginVerify(json.getString("userAccount"), json.getString("userPassword"));
        return ResultData.success(token);
    }
}
