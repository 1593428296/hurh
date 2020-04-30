package com.hurh.crowd.exception;

/**
 * @PackAgeName:com.hurh.crowd.exception
 * @ClassName:AboutRoleExistdException
 * @Description: 角色已经存在异常
 * @Author:hrh
 * @Date:2020/4/30
 */
public class AboutRoleExistdException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public AboutRoleExistdException() {
        super();
    }

    public AboutRoleExistdException(String message) {
        super(message);
    }

    public AboutRoleExistdException(String message, Throwable cause) {
        super(message, cause);
    }

    public AboutRoleExistdException(Throwable cause) {
        super(cause);
    }

    protected AboutRoleExistdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
