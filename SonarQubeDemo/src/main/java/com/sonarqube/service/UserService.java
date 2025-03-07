package com.sonarqube.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonarqube.dao.UserDao;
import com.sonarqube.model.User;



@Service
public class UserService {

   
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User registerUser(User user) {
        if (userDao.getEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
      
        userDao.registerUser(user);
        return user;
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }


    @Transactional
    public User updateUser(User user) {
       
        return userDao.updateUser(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }


    @Transactional
    public void deleteById(int id) {
      
        userDao.deleteById(id);
    }

}
