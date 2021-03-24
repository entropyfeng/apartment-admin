package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

/**
 * @author entropyfeng
 */
public class AuthResourceNotExistException extends BusinessException {

    public AuthResourceNotExistException() {
        super(ErrorCode.RESOURCE_NOT_EXIST);
    }

    public AuthResourceNotExistException(String message) {
        super(message,ErrorCode.RESOURCE_NOT_EXIST);
    }

    public AuthResourceNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthResourceNotExistException(Throwable cause) {
        super(cause);
    }

    public AuthResourceNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
