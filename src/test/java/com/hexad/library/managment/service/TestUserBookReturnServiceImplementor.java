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
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.representation.response.BookRepresentation;
import com.hexad.library.managment.representation.response.UserRepresetation;
import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

public class TestUserBookReturnServiceImplementor
{
    private UserBorrowerUpdateDAO userBorrowerUpdateDAO;

    @BeforeEach
    void setUp() throws Exception
    {
        userBorrowerUpdateDAO = Mockito.mock(UserBorrowerUpdateDAO.class);
    }

    @AfterEach
    void tearDown() throws Exception
    {
    }

    @Test
    void testBorrowBook_BoookReturnedSuccessful_NoBookInBorrowedList()
        throws UserNotFoundException, BookNotFoundException
    {
        UserBookReturnService userBookReturnServiceImplementor =
            new UserBookReturnServiceImplementor(userBorrowerUpdateDAO);
        User user = new User(1, "Test user");
        Mockito.when(userBorrowerUpdateDAO.updateUserBookReturnDetails(Mockito.anyInt(), Mockito.anyInt()))
            .thenReturn(user);
        UserRepresetation userRepresetation = userBookReturnServiceImplementor.returnBook(1, 101);
        Assertions.assertNotNull(userRepresetation);
        List<BookRepresentation> borrowedBooksOutput = userRepresetation.getBorrowedBooks();
        Assertions.assertNotNull(borrowedBooksOutput);
        Assertions.assertEquals(0, borrowedBooksOutput.size());
    }

    @Test
    void testBorrowBook_BoookReturnedSuccessful_OneBookInBorrowedList()
        throws UserNotFoundException, BookNotFoundException
    {
        UserBookReturnService userBookReturnServiceImplementor =
            new UserBookReturnServiceImplementor(userBorrowerUpdateDAO);
        User user = new User(1, "Test user");
        List<Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(new Book(101, "Lets C", "Kanitkar", 1));
        user.getBorrowedBooks().addAll(borrowedBooks);
        Mockito.when(userBorrowerUpdateDAO.updateUserBookReturnDetails(Mockito.anyInt(), Mockito.anyInt()))
            .thenReturn(user);
        UserRepresetation userRepresetation = userBookReturnServiceImplementor.returnBook(1, 101);
        Assertions.assertNotNull(userRepresetation);
        List<BookRepresentation> borrowedBooksOutput = userRepresetation.getBorrowedBooks();
        Assertions.assertNotNull(borrowedBooksOutput);
        Assertions.assertEquals(1, borrowedBooksOutput.size());
        Assertions.assertEquals("Lets C", borrowedBooksOutput.get(0).getBookName());
    }

}
