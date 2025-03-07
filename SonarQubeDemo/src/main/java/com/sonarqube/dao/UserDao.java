package com.sonarqube.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sonarqube.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.logging.Logger;

@Repository
public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User registerUser(User user) {
        entityManager.persist(user);
        logger.info("User registered: " + user);
        return user;
    }

    public User getEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUser() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Transactional
    public User updateUser(User user) {
        User updatedUser = entityManager.merge(user);
        logger.info("User updated: " + user);
        return updatedUser;
    }

    public User getById(int id) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void deleteById(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            logger.info("User deleted: " + user);
        } else {
            throw new EntityNotFoundException("User not found with id " + id);
        }
    }
}
