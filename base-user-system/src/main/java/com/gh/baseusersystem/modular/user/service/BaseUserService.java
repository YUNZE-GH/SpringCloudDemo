package com.gh.baseusersystem.modular.user.service;

import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;

import java.util.List;

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

    List<BaseUser> getListAll(BaseUser bo, int page, int rows);

    int getCountAll(BaseUser bo);

    String loginVerify(String account, String password) throws Exception;
}
