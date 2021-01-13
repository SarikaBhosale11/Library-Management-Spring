package com.hexad.library.managment.validation.book;

import com.hexad.library.managment.exception.BookNotFoundException;

public interface BookDataValidationService
{
    void checkIfValidBookIdProvided(int userId) throws BookNotFoundException;
}
