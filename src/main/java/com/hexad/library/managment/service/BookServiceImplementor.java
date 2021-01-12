package com.hexad.library.managment.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexad.library.managment.dao.BooksListDAO;
import com.hexad.library.managment.representation.BookRepresentation;
import com.hexad.library.managment.representation.LibraryRepresentation;
import com.hexad.library.managment.vo.Book;

@Service
public class BookServiceImplementor implements BookService
{

    private BooksListDAO booksReadOnlyDAOImplementor;

    @Autowired
    public BookServiceImplementor(BooksListDAO booksReadOnlyDAOImplementor)
    {
        this.booksReadOnlyDAOImplementor = booksReadOnlyDAOImplementor;
    }

    @Override
    public LibraryRepresentation getAvailableBooks()
    {
        return getLibraryData(booksReadOnlyDAOImplementor.getAvailableBooks());
    }

    /**
     * This method handles logic of converting DAO valueObjec(pojo) to representational object. This is method can have
     * business logic to add some format the output eg Date format or validate data before it is returned to caller.
     * 
     * @param books
     * @return
     */
    protected LibraryRepresentation getLibraryData(Set<Book> books)
    {
        Set<BookRepresentation> bookSet = new HashSet<>();
        // iterate over books set and copy to representational object
        books.stream().forEach((eachBook) -> bookSet.add(new BookRepresentation(eachBook.getBookId(),
            eachBook.getBookName(), eachBook.getBookAthour(), eachBook.getNumberOfCopies())));
        LibraryRepresentation library = new LibraryRepresentation();
        library.getAvailableBooks().addAll(bookSet);
        return library;
    }

}
