package com.hexad.library.managment.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexad.library.managment.representation.LibraryRepresentation;
import com.hexad.library.managment.service.BookService;

/**
 * This controller provides entry point for REST service and provides Books and avaiblity
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

    @GetMapping("/getAvailableBooks")
    public LibraryRepresentation getAvailableBooks() throws IllegalAccessException, InvocationTargetException
    {
        return bookServiceImplementor.getAvailableBooks();
    }
}
