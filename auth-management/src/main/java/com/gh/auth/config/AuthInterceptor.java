package com.gh.auth.config;

import com.gh.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/20 22:12
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private AuthService authService;


    /**
     * 预处理（在请求处理之前进行调用，即Controller方法调用之前）
     *
     * @param request  入参
     * @param response 返回参
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("执行了拦截器的preHandle方法");
        log.info("handler:" + handler.toString());
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authService.verityToken(authorization)) {
            return true;
        }
        log.info("访问失败");
//        response.sendRedirect(request.getContextPath() + "login");
        return false;
    }

    /**
     * 后处理（在拦截请求方法执行结束时调用）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("执行了拦截器的postHandle方法");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
