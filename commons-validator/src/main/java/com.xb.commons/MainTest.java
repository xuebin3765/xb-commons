package com.xb.commons;

import com.xb.commons.test.UserDTO;
import com.xb.commons.validator.Validator;

/**
 * desc:
 * author: xuebin3765@163.com
 * date: 2019/09/28
 */
public class MainTest {
    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();

        Validator.ValidResult validResult = Validator.validBean(userDTO);
        System.out.println(validResult.getErrors());
    }
}
