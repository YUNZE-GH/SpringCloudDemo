package com.gh.auth.modular.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.auth.modular.user.entity.BaseUser;
import com.gh.auth.modular.user.mapper.BaseUserMapper;
import com.gh.auth.modular.user.service.BaseUserService;
import com.gh.auth.utils.JwtUtil;
import com.gh.common.SDK;
import com.gh.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public String loginVerify(String account, String password) throws Exception {
        String pwd = baseMapper.loginVerify(account);
        if (!StringUtils.isEmpty(pwd) && pwd.equals(SDK.encryptionUtils().encryptionMD5(password))) {

            LambdaQueryWrapper<BaseUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.select(BaseUser::getId, BaseUser::getUserId, BaseUser::getUserAccount, BaseUser::getUserName);
            lambdaQueryWrapper.eq(BaseUser::getUserAccount, account);
            BaseUser bo = baseMapper.selectOne(lambdaQueryWrapper);

            // 通过登录校验，将信息存入redis，并返回token
            String token = JwtUtil.sign(bo.getUserAccount(), bo.getUserId());
            redisUtil.insertOrUpdate(token, JSONObject.parseObject(JSONObject.toJSONString(bo)));
            return token;
        }
        throw new Exception("登录异常，账号或密码错误");
    }

}
