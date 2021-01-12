package com.hexad.library.managment.representation;

import java.util.ArrayList;
import java.util.List;

public class UserRepresetation
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

    private List<BookRepresentation> borrowedBook = new ArrayList<>();

    public List<BookRepresentation> getBorrowedBook()
    {
        return borrowedBook;
    }

    public void addBookInUserBorrowedList(BookRepresentation book)
    {
        getBorrowedBook().add(book);
    }

}
