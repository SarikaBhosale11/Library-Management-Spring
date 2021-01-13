/**
 * 
 */
package com.hexad.library.managment.dao;

import java.util.List;

import com.hexad.library.managment.vo.Book;

/**
 * @author intel
 */

public interface BooksListDAO
{
    List<Book> getAvailableBooks();
}
