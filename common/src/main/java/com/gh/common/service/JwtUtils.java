package com.gh.common.service;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/13 22:35
 */
public interface JwtUtils {

    String sign(String userAccount, String userId);

    boolean verity(String token);
}
