package com.gh.baseusersystem.modular.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.gh.baseusersystem.modular.user.mapper.BaseUserMapper;
import com.gh.baseusersystem.modular.user.service.BaseUserService;
import com.gh.baseusersystem.utils.JwtUtil;
import com.gh.common.SDK;
import com.gh.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired(required = false)
    private RedisUtil redisUtil;

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    public List<BaseUser> getListAll(BaseUser bo, int page, int rows) {
        return baseMapper.selectList(null);
    }

    @Override
    public int getCountAll(BaseUser bo) {
        return baseMapper.selectCount(null);
    }

    @Override
    public String loginVerify(String account, String password) throws Exception {
        String pwd = baseMapper.loginVerify(account);
        if (!StringUtils.isEmpty(pwd) && SDK.encryptionUtils().useMD5Encryption(password).equals(pwd)) {
            BaseUser bo = baseMapper.selectOneByUserAccount(account);
            // 通过登录校验，将信息存入redis，并返回token
            String token = JwtUtil.sign(bo.getUserAccount(), bo.getUserId());
            redisUtil.insertOrUpdate(token, JSONObject.toJSONString(bo));
            return token;
        }
        throw new Exception("账号或密码错误！");
    }

}
