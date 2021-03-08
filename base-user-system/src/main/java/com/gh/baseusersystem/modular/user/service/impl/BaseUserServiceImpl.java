package com.gh.baseusersystem.modular.user.service.impl;

import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.gh.baseusersystem.modular.user.mapper.BaseUserMapper;
import com.gh.baseusersystem.modular.user.service.BaseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

    final BaseUserMapper baseUserMapper;

    @Autowired
    public BaseUserServiceImpl(BaseUserMapper baseUserMapper){
        this.baseUserMapper = baseUserMapper;
    }

    @Override
    public int getCount() {
        return baseUserMapper.getCount();
    }

    @Override
    public List<BaseUser> getListAll(BaseUser bo, int page, int rows) {
        return baseUserMapper.selectList(null);
    }

    @Override
    public int getCountAll(BaseUser bo) {
        return baseUserMapper.selectCount(null);
    }
}
