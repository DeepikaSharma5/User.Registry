package com.hms.user.registry.DAO;

import com.hms.user.registry.model.User;

import java.util.List;

public interface UserDAO {
    public User getUserById(Long id);

    List<User> getAllUsers();

    public boolean deleteUser(Long id);

    public User updateUser(Long id, User user);

    public User createUser(User user);
}
