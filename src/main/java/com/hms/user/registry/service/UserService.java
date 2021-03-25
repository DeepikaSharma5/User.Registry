package com.hms.user.registry.service;

import com.hms.user.registry.DAO.UserDAO;
import com.hms.user.registry.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserDAO userDAO;

    public User createUser(User user) {
        User userCreat = null;
        try {
            User userCreate = userDAO.createUser(user);
        } catch (Exception e) {
            LOGGER.error("Error while creating user.", e);
        }
        return userCreat;
    }

    public User getUserById(Long id) {
        User user = null;
        try {
            user = userDAO.getUserById(id);
        } catch (Exception e) {
            LOGGER.error("Error while getting user. ", e);
        }
        return user;
    }

    public List<User> getAllUsers() {
        try {
            userDAO.getAllUsers();
        } catch (Exception e) {
            LOGGER.error("Error while getting users. ", e);
        }
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(Long id) {
        boolean user = false;
        try {
            user = userDAO.deleteUser(id);
        } catch (Exception e) {
            LOGGER.error("Error while deleting user with [ id : {} ].", id, e);
        }
        return user;
    }

    public User updateUser(Long id, User user) {
        User userUpdate = null;
        try {
            userUpdate = userDAO.updateUser(id, user);
        } catch (Exception e) {
            LOGGER.error("Error while updating user with [ id : {} ].", id, e);
        }
        return userUpdate;
    }

}