package com.neu.common.utils;

public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param param
     * @return
     */
    public static boolean  isEmpty(String param){
        return param==null || "".equals(param.trim());
    }
}
