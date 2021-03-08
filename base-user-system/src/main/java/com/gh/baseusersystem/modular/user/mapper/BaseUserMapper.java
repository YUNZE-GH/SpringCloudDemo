package com.gh.baseusersystem.modular.user.mapper;

import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
