package com.commons;

import com.commons.validator.Validator;
import com.commons.test.UserDTO;

/**
 * desc:
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public class MainTest {
    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("1230000000000");
        userDTO.setPassword("123456");
        userDTO.setRePassword("123456sssss");
        userDTO.setAge(100);
        Validator.ValidResult validResult = Validator.validBean(userDTO);
        System.out.println(validResult.getErrors());
    }
}
