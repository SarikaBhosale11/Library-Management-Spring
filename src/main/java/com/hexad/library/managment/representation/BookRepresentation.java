package com.hexad.library.managment.representation;

public class BookRepresentation
{

    public BookRepresentation(int bookId, String bookName, String bookAthour, int numberOfCopies)
    {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAthour = bookAthour;
        this.numberOfCopies = numberOfCopies;
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

    private int numberOfCopies = 0;

    public int getNumberOfCopies()
    {
        return numberOfCopies;
    }

    public void increamentNumberOfCopies()
    {
        this.numberOfCopies++;
    }

    public void reduceNumberOfCopies()
    {
        this.numberOfCopies--;
    }

    public void setNumberOfCopies(int numberOfCopies)
    {
        this.numberOfCopies = numberOfCopies;
    }

}
