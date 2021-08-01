package com.gh.common.config;

import com.gh.common.exception.BusinessException;
import com.gh.common.toolsclass.ResultData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/26 23:05
 */
@RestControllerAdvice
public class OverAllExceptionHandler {

    @ExceptionHandler(value = SQLException.class)
    ResultData persistenceExceptionHandler(SQLException e, HttpServletRequest request) {
        e.printStackTrace();
        return ResultData.error("数据操作异常，请联系管理员处理！");
    }

    @ExceptionHandler(value = BusinessException.class)
    ResultData businessExceptionHandler(BusinessException e, HttpServletRequest request) {
        e.printStackTrace();
        return new ResultData(e.getCode(), null, e.getMessage());
    }

}
