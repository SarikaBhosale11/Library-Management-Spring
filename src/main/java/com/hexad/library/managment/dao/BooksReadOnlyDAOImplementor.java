package com.hexad.library.managment.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hexad.library.managment.helper.DataHelper;
import com.hexad.library.managment.vo.Book;

/**
 * ion This DAO provides implementation to access database and retrieve book related information. Here we can use
 * Hibernate/JPA to retrieve data. Temporary it uses Helper class to get static data
 * 
 * @author intel
 */
@Service
public class BooksReadOnlyDAOImplementor implements BooksListDAO
{

    @Override
    public Set<Book> getAvailableBooks()
    {
        // Here we will use Hibernate to retrive data from database
        Set<Book> avaibleBooks = new HashSet<Book>();
        avaibleBooks.addAll(DataHelper.getBooks());
        return avaibleBooks;
    }

}
