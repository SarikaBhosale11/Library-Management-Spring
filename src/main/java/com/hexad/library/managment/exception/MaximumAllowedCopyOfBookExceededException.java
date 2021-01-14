package com.hexad.library.managment.exception;

public class MaximumAllowedCopyOfBookExceededException extends Exception
{
    public MaximumAllowedCopyOfBookExceededException()
    {
    }

    public MaximumAllowedCopyOfBookExceededException(String message)
    {
        super(message);
    }
}
