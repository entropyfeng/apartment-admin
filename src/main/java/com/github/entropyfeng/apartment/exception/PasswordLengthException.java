package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

public class PasswordLengthException extends BusinessException {

    public PasswordLengthException() {
        super(ErrorCode.PASSWORD_LENGTH_SHORT);
    }

    public PasswordLengthException(String message) {
        super(message,ErrorCode.PASSWORD_LENGTH_SHORT);
    }

    public PasswordLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordLengthException(Throwable cause) {
        super(cause);
    }

    public PasswordLengthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
