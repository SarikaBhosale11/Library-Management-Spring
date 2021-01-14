package com.hexad.library.managment.service;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.representation.response.UserRepresetation;

public interface UserBookReturnService
{
    UserRepresetation returnBook(int userId, int bookId) throws UserNotFoundException, BookNotFoundException;
}
