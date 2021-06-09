package com.gh.auth.modular.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gh.auth.modular.user.entity.BaseUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
public interface BaseUserService extends IService<BaseUser> {

    String loginVerify(String account, String password) throws Exception;
}
