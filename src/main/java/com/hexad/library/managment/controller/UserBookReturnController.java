package com.hexad.library.managment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.MaximumAllowedCopyOfBookExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.representation.response.UserRepresetation;
import com.hexad.library.managment.service.UserBookReturnService;
import com.hexad.library.managment.validation.book.BookDataValidationService;
import com.hexad.library.managment.validation.user.UserDataValidationService;

/**
 * This controller provides entry point for REST service and handles book borrowing operations
 * 
 * @author intel
 */
@RestController
@RequestMapping
public class UserBookReturnController
{

    private UserBookReturnService userBookReturnServiceImplementor;

    private UserDataValidationService userDataValidationServiceImplementor;

    private BookDataValidationService bookDataValidationServiceImplementor;

    @Autowired
    public UserBookReturnController(UserBookReturnService userBookReturnServiceImplementor,
        UserDataValidationService userDataValidationServiceImplementor,
        BookDataValidationService bookDataValidationServiceImplementor)
    {
        this.userBookReturnServiceImplementor = userBookReturnServiceImplementor;
        this.userDataValidationServiceImplementor = userDataValidationServiceImplementor;
        this.bookDataValidationServiceImplementor = bookDataValidationServiceImplementor;
    }

    @PostMapping("library/returnBook/user/{userId}/book/{bookId}")
    public UserRepresetation retunBook(@PathVariable(value = "userId") Integer userId,
        @PathVariable(value = "bookId") Integer bookId) throws UserNotFoundException, BookNotFoundException,
        MaximumAllowedBooksExceededException, MaximumAllowedCopyOfBookExceededException
    {
        checkifValidateRequestDataProvided(userId, bookId);
        return userBookReturnServiceImplementor.returnBook(userId, bookId);
    }

    protected void checkifValidateRequestDataProvided(int userId, int bookId)
        throws UserNotFoundException, BookNotFoundException
    {
        this.userDataValidationServiceImplementor.checkIfValidUserIdProvided(userId);
        this.bookDataValidationServiceImplementor.checkIfValidBookIdProvided(bookId);
    }

}
