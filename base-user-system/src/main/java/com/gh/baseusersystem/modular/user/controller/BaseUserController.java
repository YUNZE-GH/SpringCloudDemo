package com.gh.baseusersystem.modular.user.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.gh.baseusersystem.modular.user.service.BaseUserService;
import com.gh.baseusersystem.utils.AjaxResult;
import com.gh.baseusersystem.utils.JwtUtil;
import com.gh.common.SDK;
import com.gh.common.enums.CodeEnum;
import com.gh.common.toolsclass.FinalProperties;
import com.gh.common.toolsclass.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/user/baseUser")
public class BaseUserController {

    final BaseUserService service;

    @Autowired
    public BaseUserController(BaseUserService service){
        this.service = service;
    }

    @GetMapping(value = "/getCount")
    public int getCount(HttpServletRequest request) {
        String token = request.getHeader("token");
        System.err.println(token);
        boolean verity = JwtUtil.verity(token);
        System.err.println(verity);
        return service.getCount();
    }

    @GetMapping(value = "/getListAll")
    public ResultData getListAll(BaseUser bo, Integer page, Integer rows){
        int count = service.getCountAll(bo);
        List<BaseUser> list = new ArrayList<>();
        if (count > 0) {
            list = service.getListAll(bo, page, rows);
        }
        return new ResultData(CodeEnum.SUCCESS.get(), count, list, "查询成功", SDK.getDateUtils().getDateTime());
    }

    @GetMapping(value = "/getUser")
    public ResultData getUser(BaseUser bo, Integer page, Integer rows){
        int count = service.getCountAll(bo);
        List<BaseUser> list = new ArrayList<>();
        if (count > 0) {
            list = service.getListAll(bo, page, rows);
        }
        return new ResultData(CodeEnum.SUCCESS.get(), count, list, "查询成功", SDK.getDateUtils().getDateTime());
    }



    @GetMapping(value = "/login")
    public AjaxResult login(@RequestBody BaseUser bo) {
        String account = bo.getUserAccount();
        String password = bo.getUserPassword();
        //身份验证
        boolean isSuccess = false;
        if ("admin".equals(account) && "123456".equals(password)) {
            isSuccess = true;
        }
        if (isSuccess) {
            //返回token
            String token = JwtUtil.sign(account, password);
            if (token != null) {
                return AjaxResult.success("成功", token);
            }
        }
        return AjaxResult.fail();
    }
}

