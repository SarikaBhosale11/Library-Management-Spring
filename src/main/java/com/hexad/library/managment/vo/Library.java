package com.hexad.library.managment.vo;

import java.util.HashSet;
import java.util.Set;

public class Library
{
    public Set<Book> availableBooks = new HashSet<>();

    public Set<Book> getAvailableBooks()
    {
        return availableBooks;
    }

}
