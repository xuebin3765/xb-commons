package com.xb.commons.validator.annotation;

import java.lang.annotation.*;

/**
 * desc: params is not null
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
    /**
     * 错误描述信息
     * @return 描述信息
     */
    public String message() default "";
}
