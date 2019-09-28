package com.xb.commons.validator.annotation;

/**
 * desc: 字段最大值注解
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public @interface Max {

    public int value();

    public String message() default "";
}
