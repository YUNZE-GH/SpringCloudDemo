package com.gh.open.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.auth.config.AuthProperties;
import com.gh.auth.modular.user.entity.BaseUser;
import com.gh.auth.modular.user.mapper.BaseUserMapper;
import com.gh.auth.modular.user.service.AuthService;
import com.gh.common.SDK;
import com.gh.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements AuthService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AuthProperties authProperties;

    @Override
    public String loginVerify(String account, String password) throws Exception {
        String pwd = baseMapper.loginVerify(account);
        if (!StringUtils.isEmpty(pwd) && pwd.equals(SDK.encryptionUtils().encryptionMD5(password))) {

            LambdaQueryWrapper<BaseUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.select(BaseUser::getId, BaseUser::getUserId, BaseUser::getUserAccount, BaseUser::getUserName);
            lambdaQueryWrapper.eq(BaseUser::getUserAccount, account);
            BaseUser bo = baseMapper.selectOne(lambdaQueryWrapper);
            JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(bo));

            // 通过登录校验，将信息存入redis，并返回token
            String token = UUID.randomUUID().toString().replace("-", "");
            json.put("token", token);

            redisUtil.insertOrUpdateByMinutes(token, json, authProperties.getTokenValidPeriod());
            return token;
        }
        throw new Exception("登录异常，账号或密码错误");
    }

    @Override
    public boolean verityToken(String token) {
        return redisUtil.hasKey(token);
    }

}
