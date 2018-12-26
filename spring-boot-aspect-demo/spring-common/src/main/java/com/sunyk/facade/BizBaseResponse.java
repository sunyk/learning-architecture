/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.facade;

import com.sunyk.enums.BizErrorCodeEnum;

import java.io.Serializable;

/**
 * @author sunyang
 * @date 2018/12/20 16:54
 */
public class BizBaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 3027996592522057089L;

    private int code;
    private String message;
    private T data;

    public BizBaseResponse() {
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc());
    }

    public BizBaseResponse(BizErrorCodeEnum errorCode){
        this(errorCode, errorCode.getDesc());
    }

    public BizBaseResponse(T data){
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc(), data);
    }

    public BizBaseResponse(BizErrorCodeEnum errorCode, String message){
        this(errorCode, message, null);
    }

    public BizBaseResponse(BizErrorCodeEnum errorCode, String message, T data) {
        this.code = errorCode.getCode();
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

