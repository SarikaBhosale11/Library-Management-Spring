package com.hexad.library.managment.vo;

import java.util.ArrayList;
import java.util.List;

public class User
{
    private int userId;

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    private String userName;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    private List<Book> borrowedBook = new ArrayList<>();

    public List<Book> getBorrowedBook()
    {
        return borrowedBook;
    }

    public void addBookInUserBorrowedList(Book book)
    {
        getBorrowedBook().add(book);
    }

}
