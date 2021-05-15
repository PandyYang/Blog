package com.pandy.blog.Exception;

import com.pandy.blog.common.Result;
import com.pandy.blog.common.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Pandy
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception ex) {
        ex.printStackTrace();
        return Result.error().codeAndMessage(ResultInfo.GLOBAL_EXCEPTION);
    }

    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public Result runtimeException(MyRuntimeException ex) {
        ex.printStackTrace();
        return Result.error().codeAndMessage(ex.getCode(), ex.getMessage());
    }
}
