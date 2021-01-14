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

    @Test
    public void testBorrowBook_BuyOneCopyOfBook() throws Exception
    {
        // Initially two copies of Book "Lets C" bookId=101 & one copy of "Head First Java" bookId=102 are available
        LibraryRepresentation libraryRepresentation = this.restTemplate
            .getForObject("http://localhost:" + port + "library/getAvailableBooks", LibraryRepresentation.class);
        Assertions.assertNotNull(libraryRepresentation);
        List<BookRepresentation> booklist = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(booklist);
        Assertions.assertEquals(2, booklist.size());
        BookRepresentation letCBook = null;
        for (BookRepresentation book : booklist) {
            if (book.getBookId() == 101) {
                letCBook = book;
                break;
            }
        }
        Assertions.assertNotNull(letCBook);
        Assertions.assertEquals(2, letCBook.getNumberOfCopiesAvailable());
        BookRepresentation HeadFirstBook = null;
        for (BookRepresentation book : booklist) {
            if (book.getBookId() == 102) {
                HeadFirstBook = book;
                break;
            }
        }
        Assertions.assertNotNull(HeadFirstBook);
        Assertions.assertEquals(1, HeadFirstBook.getNumberOfCopiesAvailable());

        // borrow "Lets C" bookId=101 book
        UserRepresetation user = this.restTemplate.postForObject(
            "http://localhost:" + port + "library/borrowBook/user/{userId}/book/{bookId}", null,
            UserRepresetation.class, Integer.valueOf(1), Integer.valueOf(101));
        Assertions.assertNotNull(user);
        List<BookRepresentation> borrwedBooks = user.getBorrowedBooks();
        Assertions.assertNotNull(borrwedBooks);
        Assertions.assertEquals(1, borrwedBooks.size());
        Assertions.assertEquals("Lets C", borrwedBooks.get(0).getBookName());
        Assertions.assertEquals(1, borrwedBooks.get(0).getNumberOfCopiesAvailable());

        // available books still show "Lets C" bookId=101 as it still has one cpy left
        libraryRepresentation = this.restTemplate.getForObject("http://localhost:" + port + "library/getAvailableBooks",
            LibraryRepresentation.class);
        Assertions.assertNotNull(libraryRepresentation);
        booklist = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(booklist);
        Assertions.assertEquals(2, booklist.size());
        letCBook = null;
        for (BookRepresentation book : booklist) {
            if (book.getBookId() == 101) {
                letCBook = book;
                break;
            }
        }
        Assertions.assertNotNull(letCBook);
        Assertions.assertEquals(1, letCBook.getNumberOfCopiesAvailable());

        // again borrow "Lets C" bookId=101, we should get exception
        // MaximumAllowedCopyOfBookExceededException is mapped to HttpStatus.UNPROCESSABLE_ENTITY 422
        ResponseEntity<String> response = restTemplate
            .postForEntity("http://localhost:" + port + "library/borrowBook/user/1/book/109", null, String.class);
        Assertions.assertEquals(422, response.getStatusCodeValue());

        // try to borrow "Head First Java" bookId=102
        user = this.restTemplate.postForObject(
            "http://localhost:" + port + "library/borrowBook/user/{userId}/book/{bookId}", null,
            UserRepresetation.class, Integer.valueOf(1), Integer.valueOf(102));
        Assertions.assertNotNull(user);
        borrwedBooks = user.getBorrowedBooks();
        Assertions.assertNotNull(borrwedBooks);
        Assertions.assertEquals(2, borrwedBooks.size()); // each of "Lets C" and "Head First Java"
        Assertions.assertEquals("Head First Java", borrwedBooks.get(1).getBookName());
        Assertions.assertEquals(0, borrwedBooks.get(1).getNumberOfCopiesAvailable());

        // Now only "Lets C" bookId=101 should be there in available list and has one copy available
        libraryRepresentation = this.restTemplate.getForObject("http://localhost:" + port + "library/getAvailableBooks",
            LibraryRepresentation.class);
        Assertions.assertNotNull(libraryRepresentation);
        booklist = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(booklist);
        Assertions.assertEquals(1, booklist.size());
        Assertions.assertEquals(1, booklist.get(0).getNumberOfCopiesAvailable());
        Assertions.assertEquals("Lets C", booklist.get(0).getBookName());

        // try to borrow "Lets C" bookId=101 for different user userId=2
        user = this.restTemplate.postForObject(
            "http://localhost:" + port + "library/borrowBook/user/{userId}/book/{bookId}", null,
            UserRepresetation.class, Integer.valueOf(2), Integer.valueOf(101));
        Assertions.assertNotNull(user);
        borrwedBooks = user.getBorrowedBooks();
        Assertions.assertNotNull(borrwedBooks);
        Assertions.assertEquals(1, borrwedBooks.size()); // each of "Lets C"
        Assertions.assertEquals("Lets C", borrwedBooks.get(0).getBookName());
        Assertions.assertEquals(0, borrwedBooks.get(0).getNumberOfCopiesAvailable());

        // Now now book should be there in available list
        libraryRepresentation = this.restTemplate.getForObject("http://localhost:" + port + "library/getAvailableBooks",
            LibraryRepresentation.class);
        Assertions.assertNotNull(libraryRepresentation);
        booklist = libraryRepresentation.getAvailableBooks();
        Assertions.assertNotNull(booklist);
        Assertions.assertEquals(0, booklist.size());

    }

}
