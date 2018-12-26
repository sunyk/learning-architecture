/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.exceptions;

import com.sunyk.enums.BizErrorCodeEnum;

import java.io.Serializable;

/**
 * 定义业务异常类
 *
 * @author sunyang
 * @date 2018/12/20 16:02
 */
public class BizException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = 5911490300734534064L;

    private BizErrorCodeEnum errorCode;
    private String errorMessage;

    public BizException() {
        super(BizErrorCodeEnum.SYSTEM_ERROR.getDesc());
        this.errorCode = BizErrorCodeEnum.SYSTEM_ERROR;
        this.errorMessage = errorCode.getDesc();
    }

    public BizException(BizErrorCodeEnum errorCode){
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDesc();
    }

    public BizException(BizErrorCodeEnum errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BizException(BizErrorCodeEnum errorCode, String errorMessage, Throwable exception){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        super.initCause(exception);
    }

    public BizErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

