package com.cclu.qqclient.common;

import lombok.Data;

/**
 * @author ChangCheng Lu
 * @date 2023/5/22 22:37
 */
@Data
public class BaseResponse <T> {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(int code) {
        this(code, null, "");
    }

    public BaseResponse(int code, String message) {
        this(code, null, message);
    }

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
