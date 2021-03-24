package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

/**
 * @author entropyfeng
 */
public class PasswordErrorException extends BusinessException {

    private String username;
    public PasswordErrorException() {
        super(ErrorCode.USER_PASSWORD_ERROR);
    }

    public PasswordErrorException(String username) {
        super(ErrorCode.USER_PASSWORD_ERROR);
        this.username=username;
    }

    public PasswordErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordErrorException(Throwable cause) {
        super(cause);
    }

    public PasswordErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getUsername() {
        return username;
    }
}
