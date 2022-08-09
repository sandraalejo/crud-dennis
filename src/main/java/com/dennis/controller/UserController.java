package com.dennis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.dennis.model.User;
import com.dennis.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/// CREATE
	@PostMapping("")
	public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
		List<User> allUser = userService.createUsers(users);
		if (allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(allUser);
	}

	/// READ
	@GetMapping("") /// READ USER
	public ResponseEntity<List<User>> getUsers() {
		List<User> allUser = userService.getUsers();
		if (allUser.isEmpty() == true) {
			ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	@GetMapping("/{parameter}/{value}")
	public ResponseEntity<List<User>> getUserByParam(@PathVariable String parameter, @PathVariable String value) {
		List<User> allUser = userService.getUsersByParam(parameter, value);
		if (allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	/// UPDATE
	@PostMapping("/{id}")
	public ResponseEntity<User> updateUserById(@RequestBody User newUser, @PathVariable String id) {

		User User = userService.updateUserById(newUser, id);

		if (ObjectUtils.isEmpty(User) == true) {
			return ResponseEntity.badRequest().body(User);
		}
		return ResponseEntity.ok().body(User);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable String id) {
		User User = userService.deleteUserById(id);

		if (ObjectUtils.isEmpty(User) == true) {
			return ResponseEntity.badRequest().body(User);
		}
		return ResponseEntity.ok().body(User);
	}

	@DeleteMapping("/{parameter}/{value}")
	public ResponseEntity<List<User>> deleteUserByParam(@PathVariable String parameter, @PathVariable String value) {
		List<User> allUser = userService.deleteUserByParam(parameter, value);
		if (allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final Exception handleAllExceptions(RuntimeException e) {
		LOGGER.error("Internal server error.", e);
		return e;
	}
}
