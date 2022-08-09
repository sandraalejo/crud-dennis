package com.dennis.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dennis.model.User;
import com.dennis.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> createUsers(List<User> users) {
		return userRepository.saveAll(users);
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public List<User> getUsersByName(String name) {
		return userRepository.findByName(name);
	}
	
	public List<User> getUsersByAge(int age) {
		return userRepository.findByAge(age);
	}

	public List<User> getUsersByParam(String param, String value) {
		List<User> users = Collections.emptyList();;
		switch(param) {
		case "name":
			users = getUsersByName(value);
			break;
		case "age":
			int age = 0;
			if(StringUtils.isNumeric(value) == true) {
				age = Integer.parseInt(value);
			}
			users = getUsersByAge(age);
			break;
		}
		
		return users;
	}
	
	public User deleteUserById(String id) {
		User ActualUser = userRepository.findById(id).orElse(null);
		if(ObjectUtils.isEmpty(ActualUser) == true) {
			return ActualUser;
		}
		userRepository.delete(ActualUser);
		return ActualUser;
	}

	public List<User> deleteUserByAge(int age) {
		List<User> Users = getUsersByAge(age);
		userRepository.deleteAll(Users);
		return Users;
	}

	public List<User> deleteUserByName(String name) {
		List<User> Users = getUsersByName(name);
		userRepository.deleteAll(Users);
		return Users;
	}
	
	public List<User> deleteUserByParam(String param, String value) {
		List<User> users = Collections.emptyList();;
		switch(param) {
		case "name":
			users = deleteUserByName(value);
			break;
		case "age":
			int age = 0;
			if(StringUtils.isNumeric(value) == true) {
				age = Integer.parseInt(value);
			}
			users = deleteUserByAge(age);
			break;
		}
		
		return users;
	}

}
