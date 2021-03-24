package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

/**
 *
 * @author entropyfeng
 */
public class AuthRoleNotExistException extends BusinessException {

    public AuthRoleNotExistException() {
        super(ErrorCode.ROLE_NOT_EXIST);
    }

    public AuthRoleNotExistException(String message) {
        super(message,ErrorCode.ROLE_NOT_EXIST);
    }

    public AuthRoleNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthRoleNotExistException(Throwable cause) {
        super(cause);
    }

    public AuthRoleNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
