package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

/**
 * @author entropyfeng
 */
public class AuthUserNotExistException extends BusinessException {
    private String username;
    public AuthUserNotExistException() {
        super(ErrorCode.USER_ACCOUNT_NOT_EXIST);
    }


    public AuthUserNotExistException(String username) {
        super(ErrorCode.USER_ACCOUNT_NOT_EXIST);
        this.username=username;
    }

    public AuthUserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthUserNotExistException(Throwable cause) {
        super(cause);
    }

    public AuthUserNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getUsername() {
        return username;
    }
}
