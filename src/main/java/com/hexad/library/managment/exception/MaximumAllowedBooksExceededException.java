package com.hexad.library.managment.exception;

public class MaximumAllowedBooksExceededException extends Exception
{
    public MaximumAllowedBooksExceededException()
    {
    }

    public MaximumAllowedBooksExceededException(String message)
    {
        super(message);
    }
}
