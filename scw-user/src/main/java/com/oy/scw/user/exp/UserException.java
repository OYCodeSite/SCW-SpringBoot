package com.oy.scw.user.exp;


import com.oy.scw.user.enums.UserExceptionEnum;

/**
 * @Author OY
 * @Date 2021/2/12
 */
public class UserException extends RuntimeException { // RuntimeException 异常会事务自动回滚

    public UserException(){}

    public UserException(UserExceptionEnum userExceptionEnum) {
        super(userExceptionEnum.getMessage());
    }
}
