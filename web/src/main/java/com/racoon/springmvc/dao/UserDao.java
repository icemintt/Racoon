package com.racoon.springmvc.dao;

/**
 * Created by icemintt on 6/9/16.
 */
import java.util.List;

import com.racoon.springmvc.model.User;
public interface UserDao {
    User findByName(String username);

    void saveUser(User user);

    void deleteUserByName(String username);

    List<User> findAllUsers();
    User findById(int id);

    User login(User user);

    boolean isCorrectLogin(User user);

}
