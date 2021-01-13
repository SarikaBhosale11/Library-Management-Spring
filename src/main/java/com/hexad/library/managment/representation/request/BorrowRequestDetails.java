package com.hexad.library.managment.representation.request;

import java.util.List;

public class BorrowRequestDetails
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

    private List<Integer> bookIdList;

    public List<Integer> getBookIdList()
    {
        return bookIdList;
    }

}
