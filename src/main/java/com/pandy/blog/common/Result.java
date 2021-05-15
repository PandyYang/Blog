package com.pandy.blog.common;

import java.util.HashMap;

/**
 * @author Pandy
 * 封装返回结果
 */
public class Result {

    HashMap<String, Object> data = new HashMap<>();
    /**
     * 返回成功 或者 失败
     */
    private boolean status;
    private String code;
    private String message;

    public Result() {
    }

    public static Result success() {
        Result result = new Result();
        result.status = true;
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.status = false;
        return result;
    }

    public Result code(String code) {
        this.setCode(code);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result codeAndMessage(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
        return this;
    }

    public Result codeAndMessage(ResultInfo resultInfo) {
        this.setCode(resultInfo.getCode());
        this.setMessage(resultInfo.getMessage());
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
