package com.hexad.library.managment.vo;

public class Book
{

    public Book(int bookId, String bookName, String bookAthour, int numberOfCopies)
    {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAthour = bookAthour;
        this.numberOfCopiesAvailable = numberOfCopies;
    }

    private int bookId;

    public int getBookId()
    {
        return bookId;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    private String bookName;

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    private String bookAthour;

    public String getBookAthour()
    {
        return bookAthour;
    }

    public void setBookAthour(String bookAthour)
    {
        this.bookAthour = bookAthour;
    }

    private int numberOfCopiesAvailable = 0;

    public int getNumberOfCopiesAvailable()
    {
        return numberOfCopiesAvailable;
    }

    public void increamentNumberOfCopiesAvailable()
    {
        this.numberOfCopiesAvailable++;
    }

    public void reduceNumberOfCopiesAvailable()
    {
        this.numberOfCopiesAvailable--;
    }

    public void InncreaseNumberOfCopiesAvailable()
    {
        this.numberOfCopiesAvailable++;
    }

    public void setNumberOfCopiesAvailable(int numberOfCopies)
    {
        this.numberOfCopiesAvailable = numberOfCopies;
    }

}
