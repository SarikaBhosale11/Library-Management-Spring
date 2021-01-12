package com.hexad.library.managment.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.hexad.library.managment.dao.BooksReadOnlyDAOImplementor;
import com.hexad.library.managment.representation.BookRepresentation;
import com.hexad.library.managment.representation.LibraryRepresentation;
import com.hexad.library.managment.vo.Book;

class TestBookServiceImplementor
{

    @BeforeEach
    void setUp() throws Exception
    {
        booksReadOnlyDAOImplementor = Mockito.mock(BooksReadOnlyDAOImplementor.class);
    }

    @AfterEach
    void tearDown() throws Exception
    {
    }

    private BooksReadOnlyDAOImplementor booksReadOnlyDAOImplementor;

    @Test
    void testGetAvailableBooks_NoBooksAvailableCondition()
    {
        BookService bookServiceImplementor = new BookServiceImplementor(booksReadOnlyDAOImplementor);
        Set<Book> avaibleBooks = new HashSet<Book>();
        Mockito.when(booksReadOnlyDAOImplementor.getAvailableBooks()).thenReturn(avaibleBooks);

        LibraryRepresentation libraryRepresentation = bookServiceImplementor.getAvailableBooks();
        Assertions.assertNotNull(libraryRepresentation);
        Set<BookRepresentation> bookSet = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(bookSet);
        Assertions.assertEquals(0, bookSet.size());
    }

    @Test
    void testGetAvailableBooks_booksAvailableCondition()
    {
        BookService bookServiceImplementor = new BookServiceImplementor(booksReadOnlyDAOImplementor);
        Set<Book> availableBooks = new HashSet<Book>();
        availableBooks.add(new Book(101, "Lets C", "Kanitkar", 2));
        availableBooks.add(new Book(102, "Head First Java", "Brain", 1));
        Mockito.when(booksReadOnlyDAOImplementor.getAvailableBooks()).thenReturn(availableBooks);

        LibraryRepresentation libraryRepresentation = bookServiceImplementor.getAvailableBooks();
        Assertions.assertNotNull(libraryRepresentation);
        Set<BookRepresentation> bookSet = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(bookSet);
        Assertions.assertEquals(2, bookSet.size());
    }

}
