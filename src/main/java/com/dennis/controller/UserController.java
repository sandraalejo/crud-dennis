package com.dennis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dennis.model.User;
import com.dennis.repository.UserRepository;
import com.dennis.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private final UserRepository userRepository;
	@Autowired 
	private final UserService userService;
	
	
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	/// CREATE
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public List<User> postUser(@RequestBody List<User> users) {
		return userRepository.saveAll(users);
	}

	/// READ
	@GetMapping("") /// READ USER
	public ResponseEntity<List<User>> getUsers() {
		List<User> allUser =  userService.getUsers();
		if(allUser.isEmpty() == true) {
			ResponseEntity.badRequest().body(allUser);
		}
	return ResponseEntity.ok().body(allUser);
	}

	@GetMapping("/{parameter}/{name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable String parameter,@PathVariable String name) {
			System.out.println("Escribio parameter " + parameter + "y en name" + name);
		List<User> allUser =  userService.getUsersByName(name);
		if(allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	@GetMapping("/age/{age}")
	public ResponseEntity<List<User>> getUserByAge(@PathVariable int age) {
		List<User> allUser =  userService.getUsersByAge(age);
		if(allUser.isEmpty() == true) {
			ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	/// UPDATE
	@PostMapping("/{id}")
	public User updateUserById(@RequestBody User newUser, @PathVariable String id) {
		newUser.setId(id);
		return userRepository.save(newUser);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public User deleteUserById(@PathVariable String id) {
		User ActualUser = userRepository.findById(id).orElse(null);
		userRepository.delete(ActualUser);
		return ActualUser;
	}

	@DeleteMapping("/age/{age}")
	public List<User> deleteUserByAge(@PathVariable int age) {
		List<User> Users = userRepository.findByAge(age);
		userRepository.deleteAll(Users);
		return Users;
	}

	@DeleteMapping("/name/{name}")
	public List<User> deleteUserByName(@PathVariable String name) {
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
