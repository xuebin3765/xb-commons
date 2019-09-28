package com.xb.commons.validator;

import com.google.common.collect.Lists;
import com.xb.commons.validator.annotation.NotNull;
import lombok.Data;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Collectors;

/**
 * desc: 校验框架静态类
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public class Validator {

    /**
     * 验证bean的所有字段
     * @param t bean
     * @param groups 校验组
     * @param <T> bean class
     * @return ValidResult
     */
    public static <T> ValidResult validBean(T t, Class<?>... groups){
        ValidResult validResult = new Validator().new ValidResult();
        if (null == t) {
            validResult.addError("requestBean", "null");
            return validResult;
        }
        Class clazz = t.getClass();
        Field[] fields = FieldUtils.getAllFields(clazz);
        if (fields.length == 0) return validResult;
        for (Field field: fields) {
            // 是否存在NotNull注解
            if (field.isAnnotationPresent(NotNull.class)){

            }

            Annotation[] annotations = field.getAnnotations();
            if (annotations != null && annotations.length > 0){
                for (Annotation annotation: annotations) {
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                    String name = annotation.annotationType().getName();
                    String anName = annotation.getClass().getName();
                    String s = annotation.getClass().getSimpleName();
                    System.out.println(anName);
                }
            }
        }
        return validResult;
    }

    /**
     * 验证bean的某一个字段
     * @param t bean
     * @param propertyName 字段
     * @return ValidResult
     */
    public static <T> ValidResult validBeanProperty(T t, String propertyName){
        ValidResult result = new Validator().new ValidResult();
        return result;
    }

    @Data
    public class ValidResult{
        /**
         * 是否有错误
         */
        private boolean hasErrors;
        /**
         * 错误信息列表
         */
        private List<ErrorMsg> errorMsgs;

        public ValidResult() {
            this.errorMsgs = Lists.newArrayList();
        }

        /**
         * 添加错误
         * @param propertyName 参数名
         * @param message 错误描述
         */
        public void addError(String propertyName, String message){
            this.errorMsgs.add(new ErrorMsg(propertyName, message));
        }

        /**
         * 获取错误的字段名称，用逗号隔开
         * 去重错误字段
         * @return 错误字段信息
         */
        public String getProperties(){
            return errorMsgs
                    .stream()
                    .map(ErrorMsg::getPropertyPath)
                    .collect(Collectors.toSet())
                    .stream()
                    .collect(Collectors.joining(", "));
        }

        /**
         * 返回所有错误信息
         * @return str
         */
        public String getErrors(){
            StringBuilder sb = new StringBuilder();
            for (ErrorMsg msg :errorMsgs) {
                sb.append("[")
                        .append(msg.getPropertyPath())
                        .append(":")
                        .append(msg.getMessage())
                        .append("]");
            }
            return sb.toString();
        }

        public boolean hasErrors(){
            return errorMsgs != null && errorMsgs.size() > 0;
        }
    }

    @Data
    public class ErrorMsg{
        private String propertyPath;
        private String message;

        public ErrorMsg(String propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }

        public String toStr(){
            return this.propertyPath + ":" + this.message;
        }
    }

}
