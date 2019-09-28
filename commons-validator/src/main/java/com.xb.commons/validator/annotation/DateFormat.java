package com.xb.commons.validator.annotation;

/**
 * desc: 日期格式校验
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public @interface DateFormat {

    public String format() default "yyyy-MM-dd HH:mm:ss";

    public String message() default "";
}
