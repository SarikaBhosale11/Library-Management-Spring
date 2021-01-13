package com.hexad.library.managment.service;

import com.hexad.library.managment.representation.response.LibraryRepresentation;

public interface BookService
{
    LibraryRepresentation getAvailableBooks();
}
