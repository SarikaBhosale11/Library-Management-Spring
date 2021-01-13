package com.hexad.library.managment.dao;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.vo.User;

public interface UserBorrowerUpdateDAO
{
    User updateUserBorrowerDetails(int userId, int bookId) throws UserNotFoundException, BookNotFoundException;
}
