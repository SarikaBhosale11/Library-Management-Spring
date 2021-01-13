package com.hexad.library.managment.validation.book;

import org.springframework.stereotype.Service;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.helper.BooksDataHelper;

@Service
public class BookDataValidationServiceImplementor implements BookDataValidationService
{

    @Override
    public void checkIfValidBookIdProvided(int bookId) throws BookNotFoundException
    {
        if (BooksDataHelper.getBookById(bookId) == null) {
            throw new BookNotFoundException("Invalid bookId provided");
        }
    }

}
