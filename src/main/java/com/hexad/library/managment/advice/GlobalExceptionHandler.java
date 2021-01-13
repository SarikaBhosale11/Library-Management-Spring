package com.hexad.library.managment.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;

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

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorect userId provided")
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(HttpServletRequest request, Exception exception)
    {
        logger.warn("Incorect userId provided", exception.getMessage());
        return "Incorect userId provided";
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorect bookid provided")
    @ExceptionHandler(BookNotFoundException.class)
    public String handleBookNotFoundException(HttpServletRequest request, Exception exception)
    {
        logger.warn("Incorect bookid provided", exception.getMessage());
        return "Incorect bookid provided";
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Maximum Allowed books limit Exceeded")
    @ExceptionHandler(MaximumAllowedBooksExceededException.class)
    public String handleMaximumAllowedBooksExceededException(HttpServletRequest request, Exception exception)
    {
        logger.warn("Maximum Allowed Books limit Exceeded ", exception.getMessage());
        return "Maximum Allowed books limit Exceeded";
    }
}
