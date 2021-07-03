package com.gh.common.toolsclass;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/3 17:07
 */
public class UserJwt {

    // 身份凭证
    private String token;

    // 用户唯一id(非主键)
    private String userId;

    // 用户账号
    private String userAccount;

    // 用户名称
    private String userName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
