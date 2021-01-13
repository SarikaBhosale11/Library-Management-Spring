package com.hexad.library.managment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexad.library.managment.representation.response.LibraryRepresentation;
import com.hexad.library.managment.service.BookService;

/**
 * This controller provides entry point for REST service and provides Books and availablity
 * 
 * @author intel
 */
@RestController
@RequestMapping
public class BooksController
{

    private BookService bookServiceImplementor;

    @Autowired
    public BooksController(BookService bookServiceImplementor)
    {
        this.bookServiceImplementor = bookServiceImplementor;
    }

    @GetMapping("library/getAvailableBooks")
    public LibraryRepresentation getAvailableBooks()
    {
        return bookServiceImplementor.getAvailableBooks();
    }
}
