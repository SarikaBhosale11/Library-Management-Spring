package com.hexad.library.managment.validation.user;

import org.springframework.stereotype.Service;

import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.helper.UsersDataHelper;

@Service
public class UserDataValidationServiceImplementor implements UserDataValidationService
{

    @Override
    public void checkIfValidUserIdProvided(int userId) throws UserNotFoundException
    {
        if (UsersDataHelper.getUserbyId(userId) == null) {
            throw new UserNotFoundException("In Valid user id provided");
        }
    }

    @Override
    public void checkIfUserAllowedToBorrowBook(int userId) throws MaximumAllowedBooksExceededException
    {
        if (UsersDataHelper.getBooksBorrowedByUser(userId).size() >= 2) {
            throw new MaximumAllowedBooksExceededException("Maximum allowed books exceeded");
        }
    }

}
