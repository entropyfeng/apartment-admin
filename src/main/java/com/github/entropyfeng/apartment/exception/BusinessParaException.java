package com.github.entropyfeng.apartment.exception;

import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;

public class BusinessParaException extends BusinessException {


    public BusinessParaException() {
        super(ErrorCode.REQUEST_PARA_ERROR);
    }

    public BusinessParaException(String message) {
        super(message, ErrorCode.REQUEST_PARA_ERROR);
    }
}
