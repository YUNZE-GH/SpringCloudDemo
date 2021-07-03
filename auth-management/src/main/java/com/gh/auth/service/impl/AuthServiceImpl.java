package com.gh.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gh.auth.config.AuthProperties;
import com.gh.auth.service.AuthService;
import com.gh.common.SDK;
import com.gh.common.enums.CodeEnum;
import com.gh.common.toolsclass.ResultData;
import com.gh.common.toolsclass.UserJwt;
import com.gh.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/22 23:55
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthProperties authProperties;

    /*@Autowired
    public AuthServiceImpl(AuthProperties authProperties){
        this.authProperties = authProperties;
    }*/

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultData authVerify(JSONObject json) {
        if (!StringUtils.isEmpty(authProperties.getTokenValidPeriod())) {
            json.put("tokenValidPeriod", authProperties.getTokenValidPeriod());
        }
        String resultResponse = SDK.httpRequest().doPostJson(authProperties.getServerPath() + "/sys-api/auth/login", json.toString());
        JSONObject jsonObject = JSONObject.parseObject(resultResponse);
        if (jsonObject.getInteger("code") != CodeEnum.SUCCESS.get()) {
            return StringUtils.isEmpty(jsonObject.get("message")) ? ResultData.error("登录失败") : ResultData.error(jsonObject.getString("message"));
        }
        UserJwt userJwt = JSONObject.toJavaObject(jsonObject.getJSONObject("data"), UserJwt.class);
        return ResultData.success(userJwt);
    }

    @Override
    public boolean verityToken(String token) {
        return redisUtil.hasKey(token);
    }
}
