package com.hexad.library.managment.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hexad.library.managment.vo.Book;
import com.hexad.library.managment.vo.User;

public class UsersDataHelper
{

    private static Map<Integer, User> userMap = new HashMap<>();

    static {
        // load user details
        userMap.put(1, new User(1, "Martin"));
        userMap.put(2, new User(2, "Martin"));
    }

    public static Map<Integer, User> getUsers()
    {
        return userMap;
    }

    public static User getUserbyId(int userId)
    {
        return userMap.get(userId);

    }

    public static List<Book> getBooksBorrowedByUser(int userId)
    {
        User user = getUserbyId(userId);
        return user.getBorrowedBooks();
    }

    public static void updateUser(int userId, User user)
    {
        if (getUserbyId(userId) != null) {
            userMap.put(userId, user);
        }

    }

    public static void updateChangedUserInfo(int userId, User user)
    {
        userMap.remove(userId);
        userMap.put(userId, user);
    }

}
