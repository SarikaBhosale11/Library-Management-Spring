package com.hexad.library.managment.representation;

import java.util.HashSet;
import java.util.Set;

public class LibraryRepresentation
{
    public Set<BookRepresentation> availableBooks = new HashSet<>();

    public Set<BookRepresentation> getAvailableBooks()
    {
        return availableBooks;
    }

}
