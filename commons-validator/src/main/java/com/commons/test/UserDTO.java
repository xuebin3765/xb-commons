package com.commons.test;

import com.commons.validator.annotation.*;
import lombok.Data;

/**
 * @Author guodandan
 * @Date 2019/9/24 23:35
 */
@Data
public class UserDTO {
    @NotNull
    @Length(min = 10)
    private String userName;
    @NotNull
    private String password;
    @NotNull
    @Equals("password")
    private String rePassword;
    @Max(100)
    @Min(20)
    private int age;
}
