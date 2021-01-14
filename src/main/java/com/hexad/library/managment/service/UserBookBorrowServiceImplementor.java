package com.hexad.library.managment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexad.library.managment.dao.UserBorrowerUpdateDAO;
import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.MaximumAllowedCopyOfBookExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.helper.BooksDataHelper;
import com.hexad.library.managment.representation.response.BookRepresentation;
import com.hexad.library.managment.representation.response.UserRepresetation;
import com.hexad.library.managment.validation.user.UserDataValidationService;
import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

@Service
public class UserBookBorrowServiceImplementor implements UserBookBorrowService
{

    private UserBorrowerUpdateDAO userBorrowerUpdateDAOImplementor;

    private UserDataValidationService userDataValidationServiceImplementor;

    @Autowired
    public UserBookBorrowServiceImplementor(UserBorrowerUpdateDAO userBorrowerUpdateDAOImplementor,
        UserDataValidationService userDataValidationServiceImplementor)
    {
        this.userBorrowerUpdateDAOImplementor = userBorrowerUpdateDAOImplementor;
        this.userDataValidationServiceImplementor = userDataValidationServiceImplementor;
    }

    @Override
    public UserRepresetation borrowBook(int userId, int bookId) throws UserNotFoundException, BookNotFoundException,
        MaximumAllowedBooksExceededException, MaximumAllowedCopyOfBookExceededException
    {
        checkIfUserAllowedToBorrowBook(userId, bookId);
        User updatedUser = userBorrowerUpdateDAOImplementor.updateUserBookBorrowDetails(userId, bookId);
        UserRepresetation userRepresetation = new UserRepresetation(updatedUser.getUserId(), updatedUser.getUserName());
        for (Book book : updatedUser.getBorrowedBooks()) {
            userRepresetation.getBorrowedBooks().add(new BookRepresentation(book.getBookId(), book.getBookName(),
                book.getBookAthour(), book.getNumberOfCopiesAvailable()));
        }
        return userRepresetation;
    }

    protected void checkIfUserAllowedToBorrowBook(int userId, int bookId)
        throws UserNotFoundException, MaximumAllowedBooksExceededException, MaximumAllowedCopyOfBookExceededException
    {
        this.userDataValidationServiceImplementor.checkIfUserHasAlreadyBorrowedTwoBooks(userId);
        this.userDataValidationServiceImplementor.checkIfUserHasAlreadyBorrowedCopyOfBook(userId,
            BooksDataHelper.getBookById(bookId));
    }

}
