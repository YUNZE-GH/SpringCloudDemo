package com.example.demo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.entity.BaseUser;
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

}
