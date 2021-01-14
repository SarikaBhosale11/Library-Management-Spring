package com.hexad.library.managment.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.hexad.library.managment.dao.UserBorrowerUpdateDAO;
import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.MaximumAllowedBooksExceededException;
import com.hexad.library.managment.exception.MaximumAllowedCopyOfBookExceededException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.representation.response.BookRepresentation;
import com.hexad.library.managment.representation.response.UserRepresetation;
import com.hexad.library.managment.validation.user.UserDataValidationService;
import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

class TestUserBorrowerServiceImplementor
{
    private UserBorrowerUpdateDAO userBorrowerUpdateDAOImplementor;

    private UserDataValidationService userDataValidationServiceImplementor;

    @BeforeEach
    void setUp() throws Exception
    {
        userBorrowerUpdateDAOImplementor = Mockito.mock(UserBorrowerUpdateDAO.class);
        userDataValidationServiceImplementor = Mockito.mock(UserDataValidationService.class);
    }

    @AfterEach
    void tearDown() throws Exception
    {
    }

    @Test
    void testBorrowBook_BoookIssuccefullyBorrowed() throws UserNotFoundException, BookNotFoundException,
        MaximumAllowedBooksExceededException, MaximumAllowedCopyOfBookExceededException
    {
        UserBorrowerServiceImplementor userBorrowerServiceImplementor =
            new UserBorrowerServiceImplementor(userBorrowerUpdateDAOImplementor, userDataValidationServiceImplementor);

        User user = new User(1, "Test user");
        List<Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(new Book(101, "Lets C", "Kanitkar", 1));
        user.getBorrowedBooks().addAll(borrowedBooks);
        Mockito.when(userBorrowerUpdateDAOImplementor.updateUserBorrowerDetails(Mockito.anyInt(), Mockito.anyInt()))
            .thenReturn(user);

        UserRepresetation userRepresetation = userBorrowerServiceImplementor.borrowBook(1, 101);
        Assertions.assertNotNull(userRepresetation);
        List<BookRepresentation> borrowedBooksOutput = userRepresetation.getBorrowedBooks();
        Assertions.assertNotNull(borrowedBooksOutput);
        Assertions.assertEquals(1, borrowedBooksOutput.size());
        Assertions.assertEquals(101, borrowedBooksOutput.get(0).getBookId());
    }

    @Test
    void testBorrowBook_UserTriedBorrowingThirdBook()
        throws UserNotFoundException, BookNotFoundException, MaximumAllowedBooksExceededException
    {
        UserBorrowerServiceImplementor userBorrowerServiceImplementor =
            new UserBorrowerServiceImplementor(userBorrowerUpdateDAOImplementor, userDataValidationServiceImplementor);

        User user = new User(1, "Test user");
        List<Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(new Book(101, "Lets C", "Kanitkar", 1));
        user.getBorrowedBooks().addAll(borrowedBooks);
        Mockito.when(userBorrowerUpdateDAOImplementor.updateUserBorrowerDetails(Mockito.anyInt(), Mockito.anyInt()))
            .thenReturn(user);
        Mockito.doThrow(MaximumAllowedBooksExceededException.class).when(userDataValidationServiceImplementor)
            .checkIfUserHasAlreadyBorrowedTwoBooks(Mockito.any());
        Assertions.assertThrows(MaximumAllowedBooksExceededException.class, () -> {
            userBorrowerServiceImplementor.borrowBook(1, 101);
        });
    }

    @Test
    void testBorrowBook_UserTriedBorrowAnotherCopyOfBook()
        throws UserNotFoundException, BookNotFoundException, MaximumAllowedBooksExceededException
    {
        UserBorrowerServiceImplementor userBorrowerServiceImplementor =
            new UserBorrowerServiceImplementor(userBorrowerUpdateDAOImplementor, userDataValidationServiceImplementor);

        User user = new User(1, "Test user");
        List<Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(new Book(101, "Lets C", "Kanitkar", 1));
        user.getBorrowedBooks().addAll(borrowedBooks);
        Mockito.when(userBorrowerUpdateDAOImplementor.updateUserBorrowerDetails(Mockito.anyInt(), Mockito.anyInt()))
            .thenReturn(user);
        Mockito.doThrow(MaximumAllowedCopyOfBookExceededException.class).when(userDataValidationServiceImplementor)
            .checkIfUserHasAlreadyBorrowedTwoBooks(Mockito.any());
        Assertions.assertThrows(MaximumAllowedBooksExceededException.class, () -> {
            userBorrowerServiceImplementor.borrowBook(1, 101);
        });
    }

}
