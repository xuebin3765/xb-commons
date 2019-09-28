package com.xb.commons.validator.annotation;

/**
 * desc: 比较两个字段的值是否相等
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public @interface Equals {

    /**
     * 错误提示信息
     * @return 描述信息
     */
    public String message() default "";
}
