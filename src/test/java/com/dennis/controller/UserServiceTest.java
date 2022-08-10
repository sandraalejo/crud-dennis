package com.dennis.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.*;

import com.dennis.model.User;
import com.dennis.repository.UserRepository;
import com.dennis.service.UserService;

@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

	@InjectMocks
	UserService userService;
	//AccountMapping accountMapping;
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private User user;

	private List<User> userList;
	private List<User> nullList;
	//private Currenc yRepository currencyRepository;

	@BeforeAll
	void setUp() {
		MockitoAnnotations.openMocks(this);	
		
		userList = new ArrayList<>();
		user.setAge(20);
		user.setName("testUser");
		userList.add(user);
		nullList = null;
	/*	account = new Account();
		account.setAccountNumber("AccNumber");
		account.setCustomerId("CustomerID");
		account.setBranchId("BranchId");
		account.setAccountTypeCode("AccountTypeCode");
		account.setCurrencyCode("Pesos Mexicanos");
		account.setStatusCode("StatusCode");*/
	}
	@DisplayName("User creation for UserService")
	@Test
	void createUsersTest(){	

		System.out.println(userService.createUsers(userList));
		Assertions.assertNotNull(userService.createUsers(userList));
	}
	@DisplayName("User creation for UserService with null user")
	@Test
	void createUserTestWithNullUser(){	
		userList.add(null);
		System.out.println(userService.createUsers(userList));
		Assertions.assertNull(userService.createUsers(userList));
	}
	@DisplayName("User creation for UserService with null User list")
	@Test
	void createUserTestWithNullUserList(){	
		System.out.println(userService.createUsers(nullList));
		Assertions.assertNull(userService.createUsers(nullList));
	}
	@DisplayName("get Users for UserService")
	@Test
	void getUsersTest(){	
		Assertions.assertNotNull(userService.getUsers());
	}

	
}