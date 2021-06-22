package com.gh.auth.service.impl;

import com.gh.auth.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/22 23:55
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean verityToken(String token) {
        return true;
    }
}
