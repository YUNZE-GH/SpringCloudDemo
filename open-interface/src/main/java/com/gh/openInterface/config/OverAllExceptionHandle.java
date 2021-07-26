package com.gh.openInterface.config;

import com.gh.common.toolsclass.ResultData;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/26 23:05
 */
@RestControllerAdvice
public class OverAllExceptionHandle {

    @ExceptionHandler(value = PersistenceException.class)
    ResultData persistenceExceptionHandle(PersistenceException e, HttpServletRequest request) {
        e.printStackTrace();
        return ResultData.error("数据操作异常，请联系管理员处理！");
    }

}
