package com.dennis.model;

import org.junit.jupiter.api.*;

public class UserTest {
	
	User user;

    @BeforeEach                                         
    void setUp() {
        user = new User();
        user.setAge(20);
        user.setName("Name");
        user.setId("id");
    }

    @Test                                               
    @DisplayName("Get user name")   
    void getName() {
    	Assertions.assertEquals("Name", user.getName());  
    }
    @Test                                               
    @DisplayName("Get user age")   
    void getage() {
    	Assertions.assertEquals(20, user.getage());  
    }
    @Test                                               
    @DisplayName("Get user id")   
    void getId() {
    	Assertions.assertEquals("id", user.getId());  
    }
    @Test                                               
    @DisplayName("Get user to string")   
    void userToString() {
    	String s = "User{id=id, name='Name', age='20'}";
    	Assertions.assertEquals(s, user.toString());  
    }
}
