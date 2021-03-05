package com.hms.user.registry.controller;

import com.hms.user.registry.DAO.UserDAO;
import com.hms.user.registry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @PostMapping("/addUser")
    User addUser(@RequestBody User user){
        return userDAO.createUser(user);
    }

    @GetMapping("/user/{id}")
    User getUser(@PathVariable Long id) throws Exception{
        User user = userDAO.getUserById(id);
        if(user == null){
            throw new Exception("No user found for id "+id);
        }
        return user;
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User user, @PathVariable Long id){
        return userDAO.updateUser(id,user);
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id){
        userDAO.deleteUser(id);
    }
}
