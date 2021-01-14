package com.hexad.library.managment.validation.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexad.library.managment.exception.BookNotFoundException;

public class TestBookDataValidationServiceImplementor
{
    BookDataValidationService bookDataValidationService;

    @BeforeEach
    void setUp() throws Exception
    {
        bookDataValidationService = new BookDataValidationServiceImplementor();
    }

    @AfterEach
    void tearDown() throws Exception
    {
    }

    @Test
    public void checkIfValidBookIdProvided_validBookId()
    {
        try {
            bookDataValidationService.checkIfValidBookIdProvided(101);
        } catch (BookNotFoundException exp) {
            Assertions.fail();
        }
    }

    @Test
    public void checkIfValidBookIdProvided_innvalidBookId()
    {
        try {
            bookDataValidationService.checkIfValidBookIdProvided(105);
        } catch (BookNotFoundException exp) {
            Assertions.assertEquals("Invalid bookId provided", exp.getMessage());
        }
    }

}
