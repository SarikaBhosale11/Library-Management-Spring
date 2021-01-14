package com.hexad.library.managment.service;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.MaximumAllowedCopyOfBookExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.representation.response.UserRepresetation;

public interface UserBorrowerService
{
    UserRepresetation borrowBook(int userId, int bookId) throws UserNotFoundException, BookNotFoundException,
        MaximumAllowedBooksExceededException, MaximumAllowedCopyOfBookExceededException;
}
