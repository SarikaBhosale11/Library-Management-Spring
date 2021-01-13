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

    public void setNumberOfCopiesAvailable(int numberOfCopies)
    {
        this.numberOfCopiesAvailable = numberOfCopies;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookAthour == null) ? 0 : bookAthour.hashCode());
        result = prime * result + bookId;
        result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (bookAthour == null) {
            if (other.bookAthour != null)
                return false;
        } else if (!bookAthour.equals(other.bookAthour))
            return false;
        if (bookId != other.bookId)
            return false;
        if (bookName == null) {
            if (other.bookName != null)
                return false;
        } else if (!bookName.equals(other.bookName))
            return false;
        return true;
    }

}
