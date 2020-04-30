package com.hurh.crowd.exception;

/**
 * @PackAgeName:com.hurh.crowd.exception
 * @ClassName:AccessForbiddenException
 * @Description: 没有登录时就访问受保护资源时抛出的异常
 * @Author:hrh
 * @Date:2020/4/26
 */
public class AccessForbiddenException extends RuntimeException{

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
