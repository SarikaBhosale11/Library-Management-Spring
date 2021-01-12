/**
 * 
 */
package com.hexad.library.managment.dao;

import java.util.Set;

import com.hexad.library.managment.vo.Book;

/**
 * @author intel
 */

public interface BooksListDAO
{
    Set<Book> getAvailableBooks();
}
