package com.sonarqube.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonarqube.dao.UserDao;
import com.sonarqube.model.User;

import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User registerUser(User user) {
        if (userDao.getEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
        logger.info("Registering user: " + user);
        userDao.registerUser(user);
        return user;
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Transactional
    public User updateUser(User user) {
        logger.info("Updating user: " + user);
        return userDao.updateUser(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public void deleteById(int id) {
        logger.info("Deleting user with id: " + id);
        userDao.deleteById(id);
    }
}
