package com.hexad.library.managment.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles possible exception and returns appropriate HTTP response
 * 
 * @author intel
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal error")
    @ExceptionHandler(RuntimeException.class)
    public String handleSQLException(HttpServletRequest request, Exception exception)
    {
        logger.warn("Runtime Exception ooccured", exception.getMessage());
        return "Internal error occured";
    }
}
