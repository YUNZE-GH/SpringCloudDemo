package com.gh.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.gh.common.toolsclass.ResultData;
import com.gh.common.toolsclass.UserJwt;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/22 23:54
 */
public interface AuthService {

    ResultData authVerify(JSONObject json);

    boolean verityToken(String token);
}
