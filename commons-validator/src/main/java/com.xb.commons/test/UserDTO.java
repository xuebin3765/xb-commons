package com.xb.commons.test;

import com.xb.commons.validator.annotation.NotNull;
import lombok.Data;

/**
 * @Author guodandan
 * @Date 2019/9/24 23:35
 */
@Data
public class UserDTO {
    @NotNull("用户名不能为空")
    private String userName;
    @NotNull("密码不能为空")
    private String password;
}
