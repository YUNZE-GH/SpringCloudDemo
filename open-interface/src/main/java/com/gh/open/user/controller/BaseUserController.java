package com.gh.open.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.gh.auth.modular.user.service.AuthService;
import com.gh.common.enums.CodeEnum;
import com.gh.common.toolsclass.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * blog-cloud
 *
 * @author 3hgh
 * @version 1.0
 * @date 2021-06-09 14:59
 **/
@RestController
@RequestMapping("/sys-api")
public class BaseUserController {

//    @Autowired
    private final AuthService authService;

    @Autowired
    public BaseUserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResultData<Object> login(@RequestBody JSONObject json) throws Exception {
        if (!json.containsKey("userAccount") || !json.containsKey("userPassword")) {
            throw new Exception("登录异常，账号和密码不能为空");
        }
        String token = authService.loginVerify(json.getString("userAccount"), json.getString("userPassword"));
        return ResultData.success(token);
    }
}
