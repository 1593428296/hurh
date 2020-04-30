package com.hurh.crowd.exception;

/**
 * @PackAgeName:com.hurh.crowd.exception
 * @ClassName:LoginAcctAlreadyExistsException
 * @Description: 更新时 检测账号是否唯一
 * @Author:hrh
 * @Date:2020/4/27
 */
public class LoginAcctAlreadyExistsForUpdateException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public LoginAcctAlreadyExistsForUpdateException() {
        super();
    }

    public LoginAcctAlreadyExistsForUpdateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyExistsForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyExistsForUpdateException(Throwable cause) {
        super(cause);
    }

    protected LoginAcctAlreadyExistsForUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
