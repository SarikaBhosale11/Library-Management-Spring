package com.hexad.library.managment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexad.library.managment.dao.UserBorrowerUpdateDAO;
import com.hexad.library.managment.exception.BookNotFoundException;
import com.hexad.library.managment.exception.UserNotFoundException;
import com.hexad.library.managment.representation.response.BookRepresentation;
import com.hexad.library.managment.representation.response.UserRepresetation;
import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

@Service
public class UserBookReturnServiceImplementor implements UserBookReturnService
{

    private UserBorrowerUpdateDAO userBorrowerUpdateDAO;

    @Autowired
    public UserBookReturnServiceImplementor(UserBorrowerUpdateDAO userBorrowerUpdateDAO)
    {
        this.userBorrowerUpdateDAO = userBorrowerUpdateDAO;
    }

    @Override
    public UserRepresetation returnBook(int userId, int bookId) throws UserNotFoundException, BookNotFoundException
    {
        User updatedUser = userBorrowerUpdateDAO.updateUserBookReturnDetails(userId, bookId);
        UserRepresetation userRepresetation = new UserRepresetation(updatedUser.getUserId(), updatedUser.getUserName());
        for (Book book : updatedUser.getBorrowedBooks()) {
            userRepresetation.getBorrowedBooks().add(new BookRepresentation(book.getBookId(), book.getBookName(),
                book.getBookAthour(), book.getNumberOfCopiesAvailable()));
        }
        return userRepresetation;
    }

}
