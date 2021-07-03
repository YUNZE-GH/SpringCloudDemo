package com.gh.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/14 22:40
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth.management")
public class AuthProperties {

    /**
     * 身份令牌有效期(时间单位：分钟)
     */
    private long tokenValidPeriod = 120;

    /**
     * 开放接口服务地址
     */
    private String serverPath;

    /**
     * 免登陆接口
     */
    private String authExcludePath;
}
