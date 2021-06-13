package com.gh.common.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gh.common.service.JwtUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/3/24 23:49
 */
public class JwtUtilsImpl implements JwtUtils {

    /**
     * 用户token的过期时间为一天
     * TODO 正式上线更换为120分钟
     */
    private final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * token私钥
     */
    private final String TOKEN_SECRET = "18067f08-f06d-4bb1-8db8-35d02116f7ff";

    /**
     * 生成签名,一段时间后后过期
     *
     * @param userAccount 账号
     * @param userId      用户id
     * @return
     */
    @Override
    public String sign(String userAccount, String userId) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带userAccount和userID生成签名
        return JWT.create().withHeader(header).withClaim("userAccount", userAccount)
                .withClaim("userId", userId).withExpiresAt(date).sign(algorithm);
    }

    @Override
    public boolean verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
