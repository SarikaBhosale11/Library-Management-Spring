package com.hexad.library.managment.validation.user;

import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;

public interface UserDataValidationService
{
    void checkIfValidUserIdProvided(int userId) throws UserNotFoundException;

    void checkIfUserAllowedToBorrowBook(int userId) throws MaximumAllowedBooksExceededException;
}
