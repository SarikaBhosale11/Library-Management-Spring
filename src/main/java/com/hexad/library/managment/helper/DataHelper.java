package com.hexad.library.managment.helper;

import java.util.HashSet;
import java.util.Set;

import com.hexad.library.managment.vo.Book;

public class DataHelper
{

    private static Set<Book> bookSet = new HashSet<>();

    static {
        bookSet.add(new Book(101, "Lets C", "Kanitkar", 2));
        bookSet.add(new Book(102, "Head First Java", "Brain", 1));
    }

    public static Set<Book> getBooks()
    {
        return bookSet;
    }
}
