package com.dennis.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dennis.model.User;
import com.dennis.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
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
	


}
