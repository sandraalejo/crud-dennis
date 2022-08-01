package com.dennis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.dennis.model.User;
import com.dennis.repository.UserMongoRepository;


@RestController
@RequestMapping("/api")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private final UserMongoRepository userRepository;
    public UserController(UserMongoRepository userRepository) {
        this.userRepository = userRepository;
    }
    ///CREATE
    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> postUser(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }

    ///READ
    @GetMapping("user") ///READ USER
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("user/name/{name}")
    public List<User> getUserByName(@PathVariable String name){
    	return userRepository.findByName(name);
    }
    @GetMapping("user/age/{age}")
    public List<User> getUserByAge(@PathVariable int age){
    	return userRepository.findByAge(age);
    }
    
    ///UPDATE
    @PostMapping("user/{id}")
    public User updateUserById(@RequestBody User newUser, @PathVariable String id){
    	newUser.setId(id);
    	return userRepository.save(newUser);
    }
    //DELETE
    @DeleteMapping("user/{id}")
    public User deleteUserById(@PathVariable String id){
    	User ActualUser = userRepository.findById(id).orElse(null);
    	userRepository.delete(ActualUser);
    	return ActualUser;
    }
    @DeleteMapping("user/age/{age}")
    public List<User> deleteUserByAge(@PathVariable int age){
    	List<User> Users = userRepository.findByAge(age);
    	userRepository.deleteAll(Users);
    	return Users;
    }
    @DeleteMapping("user/name/{name}")
    public List<User> deleteUserByName(@PathVariable String name){
    	List<User> Users = userRepository.findByName(name);
    	userRepository.deleteAll(Users);
    	return Users;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}






