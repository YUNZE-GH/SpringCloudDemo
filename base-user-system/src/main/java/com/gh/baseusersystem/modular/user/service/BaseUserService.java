package com.gh.baseusersystem.modular.user.service;

import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
public interface BaseUserService extends IService<BaseUser> {

    int getCount();
}
