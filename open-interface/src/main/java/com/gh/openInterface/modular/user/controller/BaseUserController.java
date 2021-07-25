package com.gh.openInterface.modular.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.gh.common.toolsclass.ResultData;
import com.gh.openInterface.modular.user.service.BaseUserService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/sys-api/auth")
@Slf4j
public class BaseUserController {

    private final BaseUserService baseUserService;

    @Autowired
    public BaseUserController(BaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    // 账号密码登陆接口
    @PostMapping(value = "/login")
    public ResultData login(@RequestBody JSONObject json) {
        try {
            if (!json.containsKey("userAccount") || !json.containsKey("userPassword")) {
                ResultData.error("登录异常，账号和密码不能为空");
            }
            long tokenValidPeriod = json.containsKey("tokenValidPeriod") ? json.getLong("tokenValidPeriod") : 120;
            return baseUserService.loginVerify(json.getString("userAccount"), json.getString("userPassword"), tokenValidPeriod);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.error("系统异常");
        }
    }

    // 身份令牌校验接口
    @PostMapping(value = "/verifyToken/{token}")
    public ResultData<JSONObject> verifyToken(@PathVariable("token") String token) {
        return baseUserService.verifyToken(token);
    }
}
