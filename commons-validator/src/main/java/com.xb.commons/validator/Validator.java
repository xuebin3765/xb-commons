package com.xb.commons.validator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xb.commons.utils.StringUtil;
import com.xb.commons.validator.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        try {
            List<FiledValue> filedValues = getAllHasAnnotationFiledValues(t);
            // 有相关注解，需要进行字段验证
            Optional.ofNullable(filedValues).orElse(Lists.newArrayList())
                    .stream()
                    .forEach(filedValue -> {
                        Field field = filedValue.getField();
                        Object object = filedValue.getObject();

                        if (field.isAnnotationPresent(NotNull.class)){
                            NotNull notNull = field.getAnnotation(NotNull.class);
                            String message = validate(object, notNull);
                            // 描述信息不为空，参数验证失败
                            if (StringUtils.isNotBlank(message)){
                                validResult.addError(field.getName(), message);
                            }
                        }
                    });

        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return validResult;
    }

    /**
     * 对某个值进行某类注解的验证
     * @param object 参数值
     * @param notNull 验证注解
     * @return
     */
    private static String validate(Object object, NotNull notNull) {
        String message = StringUtils.isNotBlank(notNull.value()) ? notNull.value() : "参数不能为空";
        if (object != null) message = "";
        return message;

    }

    /**
     * 获取所有有注解的字段的值
     * @param t bean
     * @return list
     */
    private static <T> List<FiledValue> getAllHasAnnotationFiledValues(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        List<FiledValue> result = Lists.newArrayList();
        Class clazz = t.getClass();
        Field[] fields = FieldUtils.getAllFields(clazz);
        if (fields != null) {
            for (Field field: fields) {
                Annotation[] annotations = field.getAnnotations();
                if (null != annotations && annotations.length > 0){
                    PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
                    Method method = descriptor.getReadMethod();
                    result.add(new Validator().new FiledValue(field, method.invoke(t)));
                }
            }
        }
        return result;
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
    @AllArgsConstructor
    @NoArgsConstructor
    public class FiledValue{
        private Field field;
        private Object object;
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
            List<String> stringList = Lists.newArrayList();
            for (ErrorMsg msg :errorMsgs) {
                stringList.add(msg.getPropertyPath()+":"+msg.getMessage());
            }
            return String.join(", ", stringList);
        }

        public String getSimpleErrors(){
            List<String> errors = Optional.ofNullable(errorMsgs).orElse(Lists.newArrayList())
                    .stream()
                    .map(ErrorMsg::getMessage)
                    .collect(Collectors.toList());
            return String.join(", ", errors);
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
    }

}
