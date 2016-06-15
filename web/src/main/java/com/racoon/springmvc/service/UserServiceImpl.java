package com.racoon.springmvc.service;

/**
 * Created by icemintt on 6/9/16.
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.racoon.springmvc.dao.UserDao;
import com.racoon.springmvc.model.User;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;
    public User findByName(String username){
        return dao.findByName(username);
    }

    public User login(User user){
        return dao.login(user);
    }

    public boolean isCorrectLogin(User user){

        return dao.isCorrectLogin(user);
    }
    public void saveUser(User user){
        dao.saveUser(user);
    }

    public void updateUser(User user){
        User entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setUsername(user.getUsername());
            entity.setPassword(user.getPassword());
        }
    }

    public void deleteUser(String username){
        dao.deleteUserByName(username);
    }

    public List<User> findAllUser(){
        return dao.findAllUsers();
    }

    public boolean isUserNameUnique(String username){
        User user=findByName(username);
        return (user ==null);
    }
}
