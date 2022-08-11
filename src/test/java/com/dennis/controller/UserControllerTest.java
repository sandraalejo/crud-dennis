package com.dennis.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.dennis.model.User;
import com.dennis.repository.UserRepository;
import com.dennis.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {

	@InjectMocks
	private UserController mockUserController;

	@Mock
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	private User user;
	private List<User> userList;

	@Autowired
	private UserController userController;

	@BeforeAll
	void setUp() {
		MockitoAnnotations.openMocks(this);

		user = new User();
		userList = new ArrayList<>();

		user.setAge(20);
		user.setName("testUser");
		userList.add(user);
	}

	@DisplayName("Create users on UserController")
	@Test
	public void createUsers() {
		ResponseEntity<List<User>> httpResponse = userController.createUsers(userList);
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.CREATED),
				() -> assertNotNull(httpResponse.getBody()));
	}

	@DisplayName("Create users on UserController with empty list")
	@Test
	public void createUsersWithEmptyList() {
		Mockito.when(userService.createUsers(userList)).thenReturn(Collections.emptyList());
		ResponseEntity<List<User>> httpResponse = mockUserController.createUsers(userList);
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST),
				() -> assertEquals(httpResponse.getBody(), Collections.emptyList()));
	}

	@DisplayName("Create users on UserController with null list")
	@Test
	public void createUsersWithNullList() {
		ResponseEntity<List<User>> httpResponse = userController.createUsers(null);
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST),
				() -> assertNull(httpResponse.getBody()));
	}

	@DisplayName("Get users on UserController")
	@Test
	public void getUsers() {
		ResponseEntity<List<User>> httpResponse = userController.getUsers();
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.OK),
				() -> assertNotNull(httpResponse.getBody()));
	}

	@DisplayName("Get users on UserController without users")
	@Test
	public void getUsersWithoutUsers() {
		Mockito.when(userService.getUsers()).thenReturn(Collections.emptyList());
		ResponseEntity<List<User>> httpResponse = mockUserController.getUsers();
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST),
				() -> assertEquals(httpResponse.getBody(), Collections.emptyList()));
	}

	/// TESTING EN DUDA
	@DisplayName("Get users on UserController by param")
	@Test
	public void getUserByParam() {
		Mockito.when(userService.getUsersByName("value")).thenReturn(userList);
		ResponseEntity<List<User>> httpResponse = mockUserController.getUserByParam("param", "value");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.OK),
				() -> assertNotNull(httpResponse.getBody()));
	}
	///// TESTIN EN DUDA

	@DisplayName("Get users on UserController without users")
	@Test
	public void getUserByParamWithoutUser() {
		ResponseEntity<List<User>> httpResponse = userController.getUserByParam("param", "value");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST),
				() -> assertEquals(httpResponse.getBody(), Collections.emptyList()));
	}

	@DisplayName("Update users on UserController")
	@Test
	public void updateUserById() {
		ResponseEntity<User> httpResponse = userController.updateUserById(user, "id");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.OK),
				() -> assertNotNull(httpResponse.getBody()));
	}

	@DisplayName("Update users on UserController with null user")
	@Test
	public void updateUserByIdWithoutParam() {
		Mockito.when(userService.updateUserById(user, "id")).thenReturn(null);
		ResponseEntity<User> httpResponse = mockUserController.updateUserById(user, "id");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST),
				() -> assertNull(httpResponse.getBody()));
	}

	@DisplayName("Delete users on UserController by Id")
	@Test
	public void deleteUserById() {
		ResponseEntity<User> httpResponse = userController.deleteUserById("id");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.OK),
				() -> assertNotNull(httpResponse.getBody()));
	}

	@DisplayName("Delete users on UserController by Id with null user")
	@Test
	public void deleteUserByIdWithNull() {
		Mockito.when(userService.deleteUserById("id")).thenReturn(null);
		ResponseEntity<User> httpResponse = userController.deleteUserById("id");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST),
				() -> assertNull(httpResponse.getBody()));
	}

	/// TEST EN DUDA
	@DisplayName("Delete users on UserController by param")
	@Test
	public void deleteUserByParam() {
		Mockito.when(userService.deleteUserByParam("param", "id")).thenReturn(userList);
		ResponseEntity<List<User>> httpResponse = userController.deleteUserByParam("param", "id");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.OK),
				() -> assertNotNull(httpResponse.getBody()));
	}
	/// TEST EN DUDA

	@DisplayName("Delete users on UserController by param without users")
	@Test
	public void deleteUserByParamWithoutUser() {
		ResponseEntity<List<User>> httpResponse = userController.deleteUserByParam("param", "id");
		Assertions.assertAll(() -> assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST),
				() -> assertEquals(httpResponse.getBody(), Collections.emptyList()));
	}
}
