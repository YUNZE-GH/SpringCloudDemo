package com.gh.open.user.service;

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

    /**
     * 登录校验
     * @param account   账号
     * @param password  密码
     * @return
     * @throws Exception
     */
    String loginVerify(String account, String password) throws Exception;

    /**
     * 验证token
     * @param token
     * @return
     */
    boolean verityToken(String token);
}
