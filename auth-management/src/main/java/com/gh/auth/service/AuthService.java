package com.gh.auth.service;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/22 23:54
 */
public interface AuthService {

    boolean verityToken(String token);
}
