package com.hexad.library.managment.validation.user;

import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.MaximumAllowedCopyOfBookExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.vo.Book;

public interface UserDataValidationService
{
    void checkIfValidUserIdProvided(int userId) throws UserNotFoundException;

    void checkIfUserHasAlreadyBorrowedTwoBooks(int userId) throws MaximumAllowedBooksExceededException;

    void checkIfUserHasAlreadyBorrowedCopyOfBook(int userId, Book book)
        throws MaximumAllowedCopyOfBookExceededException;
}
