package com.hexad.library.managment.helper;

import java.util.HashMap;
import java.util.Map;

import com.hexad.library.managment.vo.Book;

public class BooksDataHelper
{

    private static Map<Integer, Book> booksMap = new HashMap<>();

    static {
        // load book details
        booksMap.put(101, new Book(101, "Lets C", "Kanitkar", 2));
        booksMap.put(102, new Book(102, "Head First Java", "Brain", 1));
    }

    public static Map<Integer, Book> getBooks()
    {
        return booksMap;
    }

    public static Book getBookById(int bookId)
    {
        return booksMap.get(bookId);

    }

    public static void updateAvailableBookInfo(int bookId, Book book)
    {
        booksMap.remove(bookId);
        booksMap.put(bookId, book);
    }

}
