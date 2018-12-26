/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.enums;

/**
 * 公共业务异常code枚举
 *
 * @author sunyang
 * @date 2018/12/20 16:05
 */
public enum  BizErrorCodeEnum {

    /**
     * 10000-19999 共通定义
     */
    SUCCESS(10000, "success", "操作成功"),
    URL_REQUEST_ERROR(10001, "url.request.error", "异常接口调用"),
    PROCESS_FAIL(10002, "PROCESS_FAIL", "服务器处理失败"),
    TOO_MANY_REQUEST(10003, "TOO_MANY_REQUEST", "访问过于频繁"),
    PERMISSION_DENY(10004, "PERMISSION_DENY", "用户服务无权限"),
    AUTHENTICATION_EXPIRED(10005, "AUTHENTICATION_EXPIRED", "身份认证过期"),
    SUCCESS_EXIST(10006, "SUCCESS_EXIST", "记录已存在"),
    SUCCESS_ACOUNT_NOT_EXIST(10007, "SUCCESS_ACOUNT_NOT_EXIST", "账号不存在"),
    IP_LIMIT(10008, "IP_LIMIT", "IP限制"),
    PARAM_ERROR(10009, "PARAM_ERROR", "参数错误"),
    PARAM_IS_NULL(10010, "PARAM_IS_NULL", "参数为空"),
    INSUFFICIENT_USER_PERMISSIONS(10011, "INSUFFICIENT_USER_PERMISSIONS", "用户权限不足"),
    INVALID_METHOD(10012, "INVALID_METHOD", "方法名不存在"),
    REQUEST_API_NOT_FOUND(10013, "REQUEST_API_NOT_FOUND", "请求的方法名不存在"),
    INVALID_FORMAT(10014, "INVALID_FORMAT", "无效的数据格式"),
    MISSING_APP_KEY(10015, "MISSING_APP_KEY", "缺少AppKey参数"),
    INVALID_APP_KEY(10016, "INVALID_APP_KEY ", "非法的APP Key"),
    INVALID_ACCESS_TOKEN(10017, "INVALID_ACCESS_TOKEN", "无效的access token"),
    USER_DOES_NOT_EXISTS(10018, "USER_DOES_NOT_EXISTS", "用户不存在"),
    CONTENT_IS_NULL(10019, "CONTENT_IS_NULL", "内容为空"),
    CONTENT_IS_ILLEGAL(10020, "CONTENT_IS_ILLEGAL ", "包含非法内容"),
    PHONE_NUMBER_HAS_BEEN_USED(10020, "PHONE_NUMBER_HAS_BEEN_USED", "该手机号已经被使用"),
    AUTH_FAILD(10021, "AUTH_FAILD", "验证失败"),
    ILLEGAL_OPERATION(10022, "ILLEGAL_OPERATION", "非法操作"),
    USERNAME_OR_PASSWORD_ERROR(10023, "USERNAME_OR_PASSWORD_ERROR", "用户名或者密码错误"),
    OPERATION_FAILED(10024, "OPERATION_FAILED", "操作失败"),
    SYSTEM_ERROR(10025, "SYSTEM_ERROR", "系统异常"),
    CALLSERVICCE_ERROR(10026, "CALLSERVICCE_ERROR", "调用服务异常"),
    VERSION_ERROR(10027, "VERSION_ERROR", "版本号错误"),
    SUCCESS_NOT_EXIST(10028, "SUCCESS_NOT_EXIST", "数据不存在"),
    REPEAT_OPERATION(10029, "REPEAT_OPERATION", "重复操作"),
    /**
     * 结束专用定义
     */
    End(99999, "End", "不需要执行");

    private int    code;
    private String name;
    private String desc;

    BizErrorCodeEnum(int code, String name, String desc) {

        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }




}

