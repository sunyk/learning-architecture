/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunyang
 * @date 2018/12/20 16:52
 */
public class StringUtils {
    public static Random random = new Random();

    public StringUtils() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isBlank(Object str) {
        return str == null || "".equals(("" + str).trim());
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String genRandomStr(String range, int len) {
        if (!isBlank(range) && len > 0) {
            StringBuilder builder = new StringBuilder();
            char[] arr = range.toCharArray();

            for(int i = 0; i < len; ++i) {
                builder.append(arr[random.nextInt(arr.length)]);
            }

            return builder.toString();
        } else {
            return "";
        }
    }

    public static String trimRight(String s) {
        Pattern pattern = Pattern.compile("-* ");

        for(Matcher matcher = pattern.matcher(s); matcher.matches(); matcher = pattern.matcher(s)) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }

    public static String stringPading(int len, String str, String padChar, String direction) {
        String result = "";
        String tmpResult = "";
        if (str == null) {
            return result;
        } else {
            int strLen = str.trim().length();
            if (str != null && strLen > len) {
                result = str.trim().substring(0, len);
            } else if (str != null && strLen == len) {
                result = str.trim();
            } else {
                result = str.trim();

                for(int i = 0; i < len - strLen; ++i) {
                    tmpResult = tmpResult + padChar;
                }

                if ("L".equalsIgnoreCase(direction)) {
                    result = tmpResult + result;
                } else {
                    result = result + tmpResult;
                }
            }

            return result;
        }
    }

    public static String stringJoint(int len, String str, String prefix) {
        if (str.length() < len) {
            str = "0" + str;
            return stringJoint(len, str, prefix);
        } else {
            return prefix + str;
        }
    }

    public static int getLength(Object obj) {
        String str = "" + obj;
        return isNotBlank(str) ? str.length() : 0;
    }

    public static Object castToBaseObject(String value, Class<?> tClass) {
        if (isNotNullOrEmpty(value)) {
            tClass.cast(value);
        }

        return null;
    }

    public static String capitalize(String name) {
        if (isNotNullOrEmpty(name)) {
            char[] cs = name.toCharArray();
            cs[0] = (char)(cs[0] - 32);
            return String.valueOf(cs);
        } else {
            return null;
        }
    }

    public static String deleteWhitespace(String orgStr) {
        return orgStr == null ? null : orgStr.replaceAll("\\s*", "");
    }

    public static int countIndex(Integer currentPage, Integer pageSize) {
        if (currentPage != null && currentPage.intValue() != 0 && currentPage.intValue() != 1) {
            int index = (currentPage.intValue() - 1) * pageSize.intValue();
            return index;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(deleteWhitespace(" a b c defg  g    "));
    }
}
