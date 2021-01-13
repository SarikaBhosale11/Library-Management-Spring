package com.hexad.library.managment.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexad.library.managment.helper.BooksDataHelper;
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
    public List<Book> getAvailableBooks()
    {
        // Here we will use Hibernate to retrive data from database
        List<Book> avaibleBooks = new ArrayList<Book>();
        avaibleBooks.addAll(BooksDataHelper.getBooks().values());
        return avaibleBooks;
    }

}
