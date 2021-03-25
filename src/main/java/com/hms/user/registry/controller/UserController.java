package com.hms.user.registry.controller;

import com.hms.user.registry.model.User;
import com.hms.user.registry.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        LOGGER.info("Request received to get all users");
        List<User> user = userService.getAllUsers();
        if (user == null || user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOGGER.info("Users rendered from database successfully.");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        LOGGER.info("Request received to add user [ name : {} , email : {} ].", user.getName(), user.getEmail());
        if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        LOGGER.info("User with [ id: {} ] created successfully.", user.getId());
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        LOGGER.info("Request received to get user with [ id : {} ].", id);
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("User with [ id: {} ] rendered from database successfully.", id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        LOGGER.info("Request received to update user with [ id : {} , name : {} , email : {} ]", id, user.getName(), user.getEmail());
        User userExist = userService.updateUser(id, user);
        if (userExist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("User with [ id: {} ] updated successfully.", id);
        return new ResponseEntity<>(userExist, HttpStatus.OK);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
        LOGGER.info("Request received to delete user with [ id : {} ].", id);
        boolean user = userService.deleteUser(id);
        if (!user) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("User with [ id: {} ] deleted successfully.", id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
