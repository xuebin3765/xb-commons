package com.xb.commons.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * desc: 字符串工具类
 * author: xuebin3765@163.com
 * date: 2019/09/29
 */
public class StringUtil {

    /**
     * 将首字母转换成大写
     * @param var 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String firstChar2UpperCase(String var){
        if (StringUtils.isBlank(var)) return var;
        String first = var.substring(0,1);
        String last = var.substring(1);
        return first.toUpperCase() + last;
    }

}
