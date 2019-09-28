package com.xb.commons.validator.annotation;

/**
 * desc: 字段最小值注解
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public @interface Min {

    /**
     * @return 参数最小值
     */
    public int value();

    /**
     *
     * @return 描述信息
     */
    public String message() default "";
}
