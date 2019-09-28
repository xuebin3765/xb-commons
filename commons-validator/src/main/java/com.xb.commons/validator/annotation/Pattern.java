package com.xb.commons.validator.annotation;

/**
 * desc: 正则校验参数
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public @interface Pattern {

    /**
     *
     * @return 正则表达式
     */
    public String regexp();

    public String message() default "";

}
