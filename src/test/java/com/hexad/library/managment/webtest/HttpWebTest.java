package com.hexad.library.managment.webtest;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.hexad.library.managment.representation.BookRepresentation;
import com.hexad.library.managment.representation.LibraryRepresentation;

// copied from https://spring.io/guides/gs/testing-web/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpWebTest
{

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void restShouldReturnBooks() throws Exception
    {
        LibraryRepresentation libraryRepresentation = this.restTemplate
            .getForObject("http://localhost:" + port + "/getAvailableBooks", LibraryRepresentation.class);

        Assertions.assertNotNull(libraryRepresentation);
        Set<BookRepresentation> bookSet = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(bookSet);
        Assertions.assertEquals(2, bookSet.size());
    }
}
