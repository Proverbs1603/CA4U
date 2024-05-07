package com.example.ca4u.exception;

import com.example.ca4u.apiResponse.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.example.ca4u")
public class GlobalRestControllerAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleException(Exception e){
        e.printStackTrace();
        log.error("Exception catched in RestControllerAdvice : {}", e.getMessage());
        return ApiResponse.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public ApiResponse<String> handleBaseException(BaseException e){
        log.debug("Exception catched in RestControllerAdvice : {}", e.getMessage());
        return ApiResponse.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.debug("Exception catched in RestControllerAdvice : {}", e.getMessage());
        return ApiResponse.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<String> handleIllegalArgumentException(IllegalArgumentException e){
        log.debug("Exception catched in RestControllerAdvice : {}", e.getMessage());
        return ApiResponse.fail(e.getMessage());
    }
}
