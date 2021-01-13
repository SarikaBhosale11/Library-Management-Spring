package com.hexad.library.managment.vo;

import java.util.ArrayList;
import java.util.List;

public class User
{
    public User(int userId, String userName)
    {
        super();
        this.userId = userId;
        this.userName = userName;
        this.borrowedBooks = new ArrayList<Book>();
    }

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

    private List<Book> borrowedBooks = new ArrayList<>();

    public List<Book> getBorrowedBooks()
    {
        return borrowedBooks;
    }

    public void addBookInUserBorrowedList(Book book)
    {
        getBorrowedBooks().add(book);
    }

}
