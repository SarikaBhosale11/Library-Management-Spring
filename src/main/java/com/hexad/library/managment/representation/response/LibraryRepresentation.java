package com.hexad.library.managment.representation.response;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepresentation
{
    public List<BookRepresentation> availableBooks = new ArrayList<>();

    public List<BookRepresentation> getAvailableBooks()
    {
        return availableBooks;
    }

}
