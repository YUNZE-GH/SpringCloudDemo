package com.gh.baseusersystem.modular.user.controller;


import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.gh.baseusersystem.modular.user.service.BaseUserService;
import com.gh.baseusersystem.utils.AjaxResult;
import com.gh.common.service.impl.JwtUtilsImpl;
import com.gh.common.SDK;
import com.gh.common.enums.CodeEnum;
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
//        boolean verity = JwtUtilsImpl.verity(token);
        return service.getCount();
    }

    @GetMapping(value = "/getListAll")
    public ResultData getListAll(BaseUser bo, Integer page, Integer rows){
        return getResultData(bo, page, rows);
    }

    @GetMapping(value = "/getUser")
    public ResultData getUser(BaseUser bo, Integer page, Integer rows){
        return getResultData(bo, page, rows);
    }

    private ResultData getResultData(BaseUser bo, Integer page, Integer rows) {
        int count = service.getCountAll(bo);
        List<BaseUser> list = new ArrayList<>();
        if (count > 0) {
            list = service.getListAll(bo, page, rows);
        }
        return new ResultData(CodeEnum.SUCCESS.get(), count, list, "查询成功", SDK.getDateUtils().getDateTime());
    }
}

