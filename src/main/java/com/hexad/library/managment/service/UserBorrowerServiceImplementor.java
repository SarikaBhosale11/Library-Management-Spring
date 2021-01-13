package com.hexad.library.managment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexad.library.managment.dao.UserBorrowerUpdateDAO;
import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.representation.response.BookRepresentation;
import com.hexad.library.managment.representation.response.UserRepresetation;
import com.hexad.library.managment.validation.user.UserDataValidationService;
import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

@Service
public class UserBorrowerServiceImplementor implements UserBorrowerService
{

    private UserBorrowerUpdateDAO userBorrowerUpdateDAOImplementor;

    private UserDataValidationService userDataValidationServiceImplementor;

    @Autowired
    public UserBorrowerServiceImplementor(UserBorrowerUpdateDAO userBorrowerUpdateDAOImplementor,
        UserDataValidationService userDataValidationServiceImplementor)
    {
        this.userBorrowerUpdateDAOImplementor = userBorrowerUpdateDAOImplementor;
        this.userDataValidationServiceImplementor = userDataValidationServiceImplementor;
    }

    @Override
    public UserRepresetation borrowBook(int userId, int bookId)
        throws UserNotFoundException, BookNotFoundException, MaximumAllowedBooksExceededException
    {
        checkIfUserAllowedToBorrowBook(userId);
        User updatedUser = userBorrowerUpdateDAOImplementor.updateUserBorrowerDetails(userId, bookId);
        UserRepresetation userRepresetation = new UserRepresetation(updatedUser.getUserId(), updatedUser.getUserName());
        for (Book book : updatedUser.getBorrowedBooks()) {
            userRepresetation.getBorrowedBooks().add(new BookRepresentation(book.getBookId(), book.getBookName(),
                book.getBookAthour(), book.getNumberOfCopiesAvailable()));
        }
        return userRepresetation;
    }

    protected void checkIfUserAllowedToBorrowBook(int userId)
        throws UserNotFoundException, MaximumAllowedBooksExceededException
    {
        this.userDataValidationServiceImplementor.checkIfUserAllowedToBorrowBook(userId);
    }

}
