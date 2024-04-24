package com.fofo.core.controller;

import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import com.fofo.core.support.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(CoreApiException.class)
    public ResponseEntity<ApiResult<?>> handleCoreApiException(final CoreApiException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error("CoreApiException : {}", e.getMessage(), e);
            case WARN -> log.warn("CoreApiException : {}", e.getMessage(), e);
            default -> log.info("CoreApiException : {}", e.getMessage(), e);
        }
        return new ResponseEntity<>(ApiResult.error(e.getErrorType(), e.getData()), e.getErrorType().getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResult<?>> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException : {}", e.getMessage(), e);
        return handleCoreApiException(new CoreApiException(CoreErrorType.INVALID_JSON_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<?>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getField()).append("-");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append(", ");
        }
        return new ResponseEntity<>(ApiResult.error(CoreErrorType.INVALID_ARGUMENT_ERROR, stringBuilder), CoreErrorType.INVALID_ARGUMENT_ERROR.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<?>> handleException(final Exception e) {
        log.error("Exception : {}", e.getMessage(), e);
        return new ResponseEntity<>(ApiResult.error(CoreErrorType.DEFAULT_ERROR), CoreErrorType.DEFAULT_ERROR.getStatus());
    }

}
