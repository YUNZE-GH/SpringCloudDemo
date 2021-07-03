package com.gh.openInterface.modular.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.common.SDK;
import com.gh.common.enums.CodeEnum;
import com.gh.common.toolsclass.ResultData;
import com.gh.openInterface.modular.user.entity.BaseUser;
import com.gh.openInterface.modular.user.mapper.BaseUserMapper;
import com.gh.openInterface.modular.user.service.BaseUserService;
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
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

    private final RedisUtil redisUtil;

    @Autowired
    public BaseUserServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public ResultData<Object> loginVerify(String account, String password, long tokenValidPeriod) {
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
            redisUtil.insertOrUpdateByMinutes(token, json, tokenValidPeriod);
            return ResultData.success(json);
        }
        return ResultData.error("登录异常，账号或密码错误");
    }

    @Override
    public ResultData<JSONObject> verifyToken(String token) {
        if (redisUtil.hasKey(token)) {
            JSONObject json = (JSONObject) redisUtil.get(token);
            return new ResultData<>(CodeEnum.SUCCESS.get(), json, "该身份令牌通过校验，可以正常使用");
        }
        return ResultData.error("该身份令牌未通过校验，无法正常使用");
    }

}
