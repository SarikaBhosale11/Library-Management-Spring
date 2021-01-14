package com.hexad.library.managment.validation.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.MaximumAllowedCopyOfBookExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.helper.UsersDataHelper;
import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

public class TestUserDataValidationServiceImplementor
{
    UserDataValidationService userDataValidationServiceImplementor;

    @BeforeEach
    void setUp() throws Exception
    {
        userDataValidationServiceImplementor = new UserDataValidationServiceImplementor();
    }

    @AfterEach
    void tearDown() throws Exception
    {
    }

    @Test
    public void testCheckIfValidUserIdProvided_validUser()
    {
        try {
            userDataValidationServiceImplementor.checkIfValidUserIdProvided(1);
        } catch (UserNotFoundException exp) {
            Assertions.fail();
        }

    }

    @Test
    public void testCheckIfValidUserIdProvided_invalidUser()
    {
        try {
            userDataValidationServiceImplementor.checkIfValidUserIdProvided(5);
        } catch (UserNotFoundException exp) {
            Assertions.assertEquals("In Valid user id provided", exp.getMessage());
            return;
        }
        Assertions.fail();
    }

    @Test
    public void testcheckIfUserHasAlreadyBorrowedTwoBooks_oneBookBorrowed()
    {
        User user = UsersDataHelper.getUserbyId(1);
        user.getBorrowedBooks().add(new Book(101, "Lets C", "Kanitkar", 2));
        try {
            userDataValidationServiceImplementor.checkIfUserHasAlreadyBorrowedTwoBooks(1);
        } catch (MaximumAllowedBooksExceededException exp) {
            Assertions.fail();
        }

    }

    @Test
    public void testcheckIfUserHasAlreadyBorrowedTwoBooks_TwoBooksBorrowed()
    {
        User user = UsersDataHelper.getUserbyId(1);
        user.getBorrowedBooks().add(new Book(101, "Lets C", "Kanitkar", 2));
        user.getBorrowedBooks().add(new Book(102, "Head First Java", "Brain", 1));
        try {
            userDataValidationServiceImplementor.checkIfUserHasAlreadyBorrowedTwoBooks(1);
        } catch (MaximumAllowedBooksExceededException exp) {
            Assertions.assertEquals("Maximum allowed books exceeded", exp.getMessage());
            return;
        }
        Assertions.fail();
    }

    @Test
    public void testcheckIfcheckIfUserHasAlreadyBorrowedCopyOfBook_noCopyOfBookIsBorrowed()
    {
        User user = UsersDataHelper.getUserbyId(1);
        Book letsCBook = new Book(101, "Lets C", "Kanitkar", 1);
        Book headFistBoook = new Book(102, "Head First Java", "Brain", 1);
        user.getBorrowedBooks().add(letsCBook);
        try {
            userDataValidationServiceImplementor.checkIfUserHasAlreadyBorrowedCopyOfBook(1, headFistBoook);
        } catch (MaximumAllowedCopyOfBookExceededException exp) {
            Assertions.fail();
        }
    }

    @Test
    public void testcheckIfcheckIfUserHasAlreadyBorrowedCopyOfBook_copyOfBookIsBorrowed()
    {
        User user = UsersDataHelper.getUserbyId(1);
        Book letsCBook = new Book(101, "Lets C", "Kanitkar", 1);
        user.getBorrowedBooks().add(letsCBook);
        try {
            userDataValidationServiceImplementor.checkIfUserHasAlreadyBorrowedCopyOfBook(1, letsCBook);
        } catch (MaximumAllowedCopyOfBookExceededException exp) {
            Assertions.assertEquals("Maximum allowed copy of book exceeded", exp.getMessage());
            return;
        }
        Assertions.fail();
    }

}
