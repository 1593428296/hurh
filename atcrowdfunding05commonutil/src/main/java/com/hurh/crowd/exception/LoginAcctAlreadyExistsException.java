package com.hurh.crowd.exception;

/**
 * @PackAgeName:com.hurh.crowd.exception
 * @ClassName:LoginAcctAlreadyExistsException
 * @Description: 新增时 检测账号是否唯一
 * @Author:hrh
 * @Date:2020/4/27
 */
public class LoginAcctAlreadyExistsException  extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public LoginAcctAlreadyExistsException() {
        super();
    }

    public LoginAcctAlreadyExistsException(String message) {
        super(message);
    }

    public LoginAcctAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected LoginAcctAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
