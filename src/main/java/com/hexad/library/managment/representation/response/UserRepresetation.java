package com.hexad.library.managment.representation.response;

import java.util.ArrayList;
import java.util.List;

public class UserRepresetation
{
    public UserRepresetation(int userId, String userName)
    {
        super();
        this.userId = userId;
        this.userName = userName;
    }

    public UserRepresetation()
    {

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

    private List<BookRepresentation> borrowedBooks = new ArrayList<>();

    public List<BookRepresentation> getBorrowedBooks()
    {
        return borrowedBooks;
    }

    public void addBookInUserBorrowedList(BookRepresentation book)
    {
        getBorrowedBooks().add(book);
    }

}
