package com.pandy.blog.common;

/**
 * @author Pandy
 * 返回结果数据
 */
public enum ResultInfo {

    LOGIN_SUCCESS("200", "登录成功"),
    LOGIN_ERROR("400", "登录失败"),
    NOT_FOUND("404", "没有找到"),
    SUCCESS("200", "请求成功"),
    GLOBAL_EXCEPTION("101", "系统繁忙"),
    VERIFY_SUCCESS("756","登录验证成功"),
    VERIFY_FAILED("885","登录验证失败"),
    ACCESS_DENY("403", "权限不足")
    ;
    private String code;
    private String message;

     ResultInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
