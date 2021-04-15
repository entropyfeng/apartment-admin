package com.github.entropyfeng.apartment.config;

import com.github.entropyfeng.common.domain.Message;
import com.github.entropyfeng.common.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Message handleBusinessException(BusinessException e){
        Message message=new Message();
        message.setSuccess(false);
        message.setMsg(e.getMessage());
        message.setErrorMessage(e.getErrorCode().getErrCode());
        return message;
    }
}
