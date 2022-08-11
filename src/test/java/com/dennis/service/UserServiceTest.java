package com.dennis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.*;

import com.dennis.model.User;
import com.dennis.repository.UserRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	private UserRepository userRepository;

	private User user;
	private List<User> userList;
	private List<User> nullList;

	@BeforeAll
	void setUp() {
		MockitoAnnotations.openMocks(this);

		user = new User();
		userList = new ArrayList<>();
		user.setAge(20);
		user.setName("testUser");
		userList.add(user);
		nullList = null;

	}

	@DisplayName("User creation for UserService")
	@Test
	void createUsersTest() {
		Assertions.assertNotNull(userService.createUsers(userList));
	}

	@DisplayName("User creation for UserService with null user")
	@Test
	void createUserTestWithNullUser() {
		userList.add(null);
		Assertions.assertNull(userService.createUsers(userList));
	}

	@DisplayName("User creation for UserService with null User list")
	@Test
	void createUserTestWithNullUserList() {
		Assertions.assertNull(userService.createUsers(nullList));
	}

	@DisplayName("User creation for UserService with empty User list")
	@Test
	void createUserTestWithEmptyUserList() {
		Assertions.assertNull(userService.createUsers(Collections.emptyList()));
	}

	@DisplayName("Get Users for UserService")
	@Test
	void getUsersTest() {
		Assertions.assertNotNull(userService.getUsers());
	}

	@DisplayName("Get Users by name for UserService")
	@Test
	void getUsersTestByName() {
		Assertions.assertNotNull(userService.getUsersByName("Dennis"));
	}

	@DisplayName("Get Users by name for UserService with empty name")
	@Test
	void getUsersTestByNameWithEmptyName() {
		Assertions.assertNotNull(userService.getUsersByName(""));
	}

	@DisplayName("Get Users by name for UserService with null name")
	@Test
	void getUsersTestByNameWithNullName() {
		Assertions.assertNotNull(userService.getUsersByName(null));
	}

	@DisplayName("Get Users by age for UserService")
	@Test
	void getUsersByAgeTest() {
		Assertions.assertNotNull(userService.getUsersByAge(10));
	}

	@DisplayName("Get Users by age for UserService with negative age")
	@Test
	void getUsersTestByAgeNegative() {
		Assertions.assertNotNull(userService.getUsersByAge(-1));
	}

	@DisplayName("Get Users by parameter for UserService case name")
	@Test
	void getUsersByParamTestName() {
		Assertions.assertNotNull(userService.getUsersByParam("name", "value"));
	}

	@DisplayName("Get Users by parameter for UserService case name with value is empty")
	@Test
	void getUsersByParamTestNameEmptyValue() {
		Assertions.assertNotNull(userService.getUsersByParam("name", ""));
	}

	@DisplayName("Get Users by parameter for UserService case name with value is null")
	@Test
	void getUsersByParamTestNameNullValue() {
		Assertions.assertNotNull(userService.getUsersByParam("name", null));
	}

	@DisplayName("Get Users by parameter for UserService case age with value not is numeric")
	@Test
	void getUsersByParamTestAgeNotNumeric() {
		Assertions.assertNotNull(userService.getUsersByParam("age", "value"));
	}

	@DisplayName("Get Users by parameter for UserService case age with value is numeric")
	@Test
	void getUsersByParamTestAgeNumeric() {
		Assertions.assertNotNull(userService.getUsersByParam("age", "10"));
	}

	@DisplayName("Get Users by parameter for UserService case age with value is empty")
	@Test
	void getUsersByParamTestAge() {
		Assertions.assertNotNull(userService.getUsersByParam("age", ""));
	}

	@DisplayName("Get Users by parameter for UserService case age with value is null")
	@Test
	void getUsersByParamTestAgeNull() {
		Assertions.assertNotNull(userService.getUsersByParam("age", null));
	}

	@DisplayName("Update Users by id for UserService")
	@Test
	void updateUserById() {
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Assertions.assertNotNull(userService.updateUserById(user, "id"));
	}

	@DisplayName("Update Users by id for UserService with null user")
	@Test
	void updateUserByIdWithNullUser() {
		Assertions.assertNull(userService.updateUserById(null, "id"));
	}

	@DisplayName("Delete Users by name for UserService")
	@Test
	void deleteUsersTestByName() {
		Assertions.assertNotNull(userService.deleteUserByName("value"));
	}

	@DisplayName("Delete Users by name for UserService with empty name")
	@Test
	void deleteUsersTestByNameWithEmptyName() {
		Assertions.assertNotNull(userService.deleteUserByName(""));
	}

	@DisplayName("Delete Users by name for UserService with null name")
	@Test
	void deleteUsersTestByNameWithNullName() {
		Assertions.assertNotNull(userService.deleteUserByName(null));
	}

	@DisplayName("Delete Users by age for UserService")
	@Test
	void deleteUsersByAgeTest() {
		Assertions.assertNotNull(userService.deleteUserByAge(10));
	}

	@DisplayName("Delete Users by age for UserService with negative age")
	@Test
	void deleteUsersTestByAgeNegative() {
		Assertions.assertNotNull(userService.deleteUserByAge(-1));
	}

	// TEST CON DUDA
	@DisplayName("Delete Users by id for UserService")
	@Test
	void deleteUserById() {
		// Mockito.when(userRepository.findById("id").orElse(null)).thenReturn(Collections.emptyList());
		User userreturn = userService.deleteUserById("id");
		Assertions.assertNotNull(userreturn);
	}
	/// TEST CON DUDA

	@DisplayName("Delete Users by parameter for UserService case name")
	@Test
	void deleteUsersByParamTestName() {
		Assertions.assertNotNull(userService.deleteUserByParam("name", "value"));
	}

	@DisplayName("Delete Users by parameter for UserService case name with value is empty")
	@Test
	void deleteUsersByParamTestNameEmptyValue() {
		Assertions.assertNotNull(userService.deleteUserByParam("name", ""));
	}

	@DisplayName("Delete Users by parameter for UserService case name with value is null")
	@Test
	void deleteUsersByParamTestNameNullValue() {
		Assertions.assertNotNull(userService.deleteUserByParam("name", null));
	}

	@DisplayName("Delete Users by parameter for UserService case age with value is numeric")
	@Test
	void deleteUsersByParamTestAgeNumeric() {
		Assertions.assertNotNull(userService.deleteUserByParam("age", "10"));
	}

	@DisplayName("Delete Users by parameter for UserService case age with value is empty")
	@Test
	void deleteUsersByParamTestAgeWithEmptyValue() {
		Assertions.assertNotNull(userService.deleteUserByParam("age", ""));
	}

	@DisplayName("Delete Users by parameter for UserService case age with value is null")
	@Test
	void deleteUsersByParamTestAgeNull() {
		Assertions.assertNotNull(userService.deleteUserByParam("age", null));
	}

}