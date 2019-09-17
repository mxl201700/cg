package cn.itcast.framework.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Package: cn.itcast.framework.exception
 * Author: Mxl
 * Date: Created in 2019/8/16 8:53
 **/
@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * 异常处理类
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
