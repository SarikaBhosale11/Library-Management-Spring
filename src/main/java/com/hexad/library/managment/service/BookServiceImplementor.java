package com.hexad.library.managment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexad.library.managment.dao.BooksListDAO;
import com.hexad.library.managment.representation.response.BookRepresentation;
import com.hexad.library.managment.representation.response.LibraryRepresentation;
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
    protected LibraryRepresentation getLibraryData(List<Book> books)
    {
        List<BookRepresentation> bookList = new ArrayList<>();
        // iterate over books set, add to representational data only if it has any available copy.
        for (Book book : books) {
            int numberOfCopiesAvailable = book.getNumberOfCopiesAvailable();
            if (numberOfCopiesAvailable > 0) {
                bookList.add(new BookRepresentation(book.getBookId(), book.getBookName(), book.getBookAthour(),
                    numberOfCopiesAvailable));
            }
        }
        LibraryRepresentation library = new LibraryRepresentation();
        library.getAvailableBooks().addAll(bookList);
        return library;
    }

}
