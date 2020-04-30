package com.hurh.crowd.exception;

/**
 * @PackAgeName:com.hurh.crowd.exception
 * @ClassName:LoginFailedException
 * @Description: 登录失败之后抛出的异常
 * @Author:hrh
 * @Date:2020/4/25
 */
public class LoginFailedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }
}
