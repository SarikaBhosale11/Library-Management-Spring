package com.hexad.library.managment.validation.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.MaximumAllowedCopyOfBookExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.helper.UsersDataHelper;
import com.hexad.library.managment.vo.Book;

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
    public void checkIfUserHasAlreadyBorrowedTwoBooks(int userId) throws MaximumAllowedBooksExceededException
    {
        List<Book> booksBorrwedByUser = UsersDataHelper.getBooksBorrowedByUser(userId);
        if (booksBorrwedByUser.size() >= 2) {
            throw new MaximumAllowedBooksExceededException("Maximum allowed books exceeded");
        }
    }

    @Override
    public void checkIfUserHasAlreadyBorrowedCopyOfBook(int userId, Book book)
        throws MaximumAllowedCopyOfBookExceededException
    {
        List<Book> booksBorrwedByUser = UsersDataHelper.getBooksBorrowedByUser(userId);
        if (booksBorrwedByUser.contains(book)) {
            throw new MaximumAllowedCopyOfBookExceededException("Maximum allowed copy of book exceeded");
        }
    }

}
