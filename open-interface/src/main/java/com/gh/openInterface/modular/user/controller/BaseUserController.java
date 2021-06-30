package com.gh.openInterface.modular.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.gh.common.enums.CodeEnum;
import com.gh.common.toolsclass.ResultData;
import com.gh.openInterface.modular.user.service.BaseUserService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/sys-api")
@Slf4j
public class BaseUserController {

    private final BaseUserService baseUserService;

    @Autowired
    public BaseUserController(BaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    @PostMapping(value = "/auth/login")
    public ResultData<Object> login(@RequestBody JSONObject json) {
        try {
            if (!json.containsKey("userAccount") || !json.containsKey("userPassword")) {
                ResultData.error("登录异常，账号和密码不能为空");
            }
            return baseUserService.loginVerify(json.getString("userAccount"), json.getString("userPassword"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.error("系统异常");
        }
    }
}
