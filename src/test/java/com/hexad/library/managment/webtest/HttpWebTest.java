package com.hexad.library.managment.webtest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.hexad.library.managment.representation.response.BookRepresentation;
import com.hexad.library.managment.representation.response.LibraryRepresentation;
import com.hexad.library.managment.representation.response.UserRepresetation;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpWebTest
{

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAvailabLeBooks() throws Exception
    {
        LibraryRepresentation libraryRepresentation = this.restTemplate
            .getForObject("http://localhost:" + port + "library/getAvailableBooks", LibraryRepresentation.class);

        Assertions.assertNotNull(libraryRepresentation);
        List<BookRepresentation> booklist = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(booklist);
        Assertions.assertEquals(2, booklist.size());
    }

    @Test
    public void testBorrowBook_BookisAddedInBorrowerList() throws Exception
    {
        UserRepresetation user = this.restTemplate.postForObject(
            "http://localhost:" + port + "library/borrowBook/user/{userId}/book/{bookId}", null,
            UserRepresetation.class, Integer.valueOf(1), Integer.valueOf(102));

        Assertions.assertNotNull(user);
        List<BookRepresentation> borrwedBooks = user.getBorrowedBooks();
        Assertions.assertNotNull(borrwedBooks);
        Assertions.assertEquals(1, borrwedBooks.size());
        Assertions.assertEquals("Head First Java", borrwedBooks.get(0).getBookName());
    }

    @Test
    public void testBorrowBook_BookIsRemovedFromLibrary() throws Exception
    {
        UserRepresetation user = this.restTemplate.postForObject(
            "http://localhost:" + port + "library/borrowBook/user/{userId}/book/{bookId}", null,
            UserRepresetation.class, Integer.valueOf(1), Integer.valueOf(102));

        Assertions.assertNotNull(user);
        List<BookRepresentation> borrwedBooks = user.getBorrowedBooks();
        Assertions.assertNotNull(borrwedBooks);
        Assertions.assertEquals(1, borrwedBooks.size());

        LibraryRepresentation libraryRepresentation = this.restTemplate
            .getForObject("http://localhost:" + port + "library/getAvailableBooks", LibraryRepresentation.class);

        Assertions.assertNotNull(libraryRepresentation);
        List<BookRepresentation> booklist = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(booklist);
        Assertions.assertEquals(1, booklist.size());
        // user has borrowed book (102) so only available is 101
        Assertions.assertEquals(101, booklist.get(0).getBookId());
    }

    @Test
    public void testBorrowBook_InValidUserIdProvided() throws Exception
    {
        ResponseEntity<String> response = restTemplate
            .postForEntity("http://localhost:" + port + "library/borrowBook/user/5/book/102", null, String.class);
        Assertions.assertEquals(422, response.getStatusCodeValue());
    }

    @Test
    public void testBorrowBook_InValidBookIdProvided() throws Exception
    {
        ResponseEntity<String> response = restTemplate
            .postForEntity("http://localhost:" + port + "library/borrowBook/user/1/book/109", null, String.class);
        Assertions.assertEquals(422, response.getStatusCodeValue());
    }

}
