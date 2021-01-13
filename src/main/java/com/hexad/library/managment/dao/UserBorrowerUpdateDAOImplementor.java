package com.hexad.library.managment.dao;

import org.springframework.stereotype.Component;

import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.helper.BooksDataHelper;
import com.hexad.library.managment.helper.UsersDataHelper;
import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

@Component
public class UserBorrowerUpdateDAOImplementor implements UserBorrowerUpdateDAO
{

    @Override
    public User updateUserBorrowerDetails(int userId, int bookId) throws UserNotFoundException, BookNotFoundException
    {
        Book book = BooksDataHelper.getBookById(bookId);
        User user = UsersDataHelper.getUserbyId(userId);
        addBookToUserBorrowedList(user, book);
        reduceNumberOfCopiesOfBook(book);
        UsersDataHelper.updateChangedUserInfo(userId, user);
        BooksDataHelper.updateAvailableBookInfo(bookId, book);
        return UsersDataHelper.getUserbyId(userId);
    }

    protected void addBookToUserBorrowedList(User user, Book book)
    {
        user.addBookInUserBorrowedList(book);
    }

    protected void reduceNumberOfCopiesOfBook(Book book)
    {
        book.reduceNumberOfCopiesAvailable();
    }

}
