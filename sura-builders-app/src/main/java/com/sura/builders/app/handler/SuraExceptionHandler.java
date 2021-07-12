package com.sura.builders.app.handler;

import com.sura.builders.common.ex.ApiError;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.ex.ErrorCodes;
import com.sura.builders.common.ex.ErrorMessages;
import com.sura.builders.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class SuraExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageService messageService;

    @ExceptionHandler(SuraException.class)
    public ResponseEntity<ApiError> exceptionHandler(SuraException ex) {
        ApiError apiMessage = new ApiError(ex.getHttpStatus(), ex.getMessage(), ex.getCode(), ex);
        return new ResponseEntity<>(apiMessage, new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleAllUncaughtException(Exception ex, WebRequest request) {
        ApiError apiMessage = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, messageService.getMessage(ErrorMessages.UNKNOWN_ERROR), ErrorCodes.UNKNOWN , ex);
        return new ResponseEntity<>(apiMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
