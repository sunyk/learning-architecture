/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.util;

/**
 * @author sunyang
 * @date 2018/12/20 16:50
 */
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonBinder {
    private static Map<Include, JsonBinder> mappers = new HashMap();
    private ObjectMapper mapper = new ObjectMapper();

    public JsonBinder(Include inclusion) {
        SerializationConfig config = this.mapper.getSerializationConfig();
        config.withPropertyInclusion(config.getDefaultPropertyInclusion().withValueInclusion(inclusion));
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        this.mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    private static synchronized JsonBinder createBinder(Include inclusion) {
        JsonBinder returnValue = (JsonBinder) mappers.get(inclusion);
        if (returnValue == null) {
            returnValue = new JsonBinder(inclusion);
            mappers.put(inclusion, returnValue);
        }

        return returnValue;
    }

    public static JsonBinder buildNormalBinder() {
        return createBinder(Include.ALWAYS);
    }

    public static JsonBinder buildNonNullBinder() {
        return createBinder(Include.NON_NULL);
    }

    public static JsonBinder buildNonEmptyBinder() {
        return createBinder(Include.NON_EMPTY);
    }

    public static JsonBinder buildNonDefaultBinder() {
        return createBinder(Include.NON_DEFAULT);
    }

    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isNullOrEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, clazz);
            } catch (IOException var4) {
                return null;
            }
        }
    }

    public <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isNullOrEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, typeReference);
            } catch (IOException var4) {
                return null;
            }
        }
    }

    public <T> List<T> fromJsonList(String jsonString, Class<T> clazz) {
        if (StringUtils.isNullOrEmpty(jsonString)) {
            return null;
        } else {
            try {
                JavaType javaType = this.mapper.getTypeFactory().constructParametricType(List.class, new Class[]{clazz});
                return (List) this.mapper.readValue(jsonString, javaType);
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public String toJson(Object object) {
        if (object != null) {
            try {
                return this.mapper.writeValueAsString(object);
            } catch (IOException var3) {
                return null;
            }
        } else {
            return null;
        }
    }

    public void setDateFormat(String pattern) {
        if (!StringUtils.isNullOrEmpty(pattern)) {
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            this.mapper.getSerializationConfig().with(dateFormat);
            this.mapper.getDeserializationConfig().with(dateFormat);
        }

    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }
}


