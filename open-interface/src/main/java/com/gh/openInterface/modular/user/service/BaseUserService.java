package com.gh.openInterface.modular.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gh.common.toolsclass.ResultData;
import com.gh.openInterface.modular.user.entity.BaseUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
public interface BaseUserService extends IService<BaseUser> {

    /**
     * 登录校验
     * @param account   账号
     * @param password  密码
     * @return
     */
    ResultData<Object> loginVerify(String account, String password);
}
