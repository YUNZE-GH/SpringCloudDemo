package com.gh.baseusersystem.modular.user.mapper;

import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@Repository
public interface BaseUserMapper extends BaseMapper<BaseUser> {
    int getCount();

    @Select("select user_password from base_user where user_account = #{userAccount}")
    String loginVerify(String userAccount);

    @Select("select user_id,user_account,user_name from base_user where user_account = #{userAccount}")
    BaseUser selectOneByUserAccount(String userAccount);
}
