package com.xb.commons.validator.annotation;

/**
 * desc: 参数长度校验
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public @interface Length {
    /**
     * 参数长度最小值
     * @return 最小长度
     */
    public int min() default 0;

    /**
     * 参数长度最大值
     * @return 最大长度
     */
    public int max() default 10;

    /**
     * 错误描述信息
     * @return 错误信息
     */
    public String message() default "";
}
