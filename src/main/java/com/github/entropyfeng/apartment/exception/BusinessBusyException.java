package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

public class BusinessBusyException extends BusinessException {

    public BusinessBusyException() {
        super(ErrorCode.SERVER_BUSINESS);
    }

    public BusinessBusyException(String message) {
        super(message, ErrorCode.SERVER_BUSINESS);
    }
}
