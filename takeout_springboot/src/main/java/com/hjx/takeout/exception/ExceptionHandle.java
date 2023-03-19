package com.hjx.takeout.exception;

import com.hjx.takeout.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(Exception e) throws Exception{
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e) throws Exception{
        return Result.error(e.getMessage());
    }
}
