package com.pandy.blog.auth;

import com.pandy.blog.common.ResultInfo;
import com.pandy.blog.exception.MyRuntimeException;
import org.springframework.security.access.AccessDeniedException;

/**
 * @author Pandy
 */
public class MyAccessDeniedException extends AccessDeniedException {
    public MyAccessDeniedException(String msg) {
        super(msg);
        throw new MyRuntimeException(ResultInfo.ACCESS_DENY);
    }
}
