package com.gh.open.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gh.auth.modular.user.entity.BaseUser;
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

    @Select("select user_password from base_user where user_account = #{userAccount}")
    String loginVerify(String userAccount);

}
