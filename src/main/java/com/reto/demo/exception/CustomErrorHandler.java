package com.reto.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomErrorHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ExceptionResponse> handleCustomException(BusinessException exception) {
        log.warn("Error handleCustomException: ", exception);
        return ResponseEntity.unprocessableEntity().body(new ExceptionResponse(exception.getCode(), exception.getErrorMsg()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        log.error("Error handleException: ", exception);
        return ResponseEntity.unprocessableEntity().body(new ExceptionResponse(422, exception.getMessage()));
    }
}
