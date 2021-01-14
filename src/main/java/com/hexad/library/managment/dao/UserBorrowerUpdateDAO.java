package com.hexad.library.managment.dao;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.vo.User;

public interface UserBorrowerUpdateDAO
{
    User updateUserBookBorrowDetails(int userId, int bookId) throws UserNotFoundException, BookNotFoundException;

    User updateUserBookReturnDetails(int userId, int bookId) throws UserNotFoundException, BookNotFoundException;
}
