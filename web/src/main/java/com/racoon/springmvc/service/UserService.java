package com.racoon.springmvc.service;

/**
 * Created by icemintt on 6/9/16.
 */
import java.util.List;

import com.racoon.springmvc.model.User;
public interface UserService {
    User findByName(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String username);

    List<User> findAllUser();

    User login(User user);

    boolean isCorrectLogin(User user);

    boolean isUserNameUnique(String username);
}
