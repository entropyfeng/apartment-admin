package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

public class PasswordStrengthException  extends BusinessException {

    public PasswordStrengthException() {
        super(ErrorCode.PASSWORD_NOT_STRONG);
    }

    public PasswordStrengthException(String message) {
        super(message,ErrorCode.PASSWORD_NOT_STRONG);
    }

    public PasswordStrengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordStrengthException(Throwable cause) {
        super(cause);
    }

    public PasswordStrengthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
