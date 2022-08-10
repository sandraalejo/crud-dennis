package com.dennis.model;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String id;
	private String name;
	private int age;

	public void setName(String n) {
		this.name = n;
	}

	public void setId(String n) {
		this.id = n;
	}

	public void setAge(int n) {
		this.age = n;
	}

	public String getName() {
		return this.name;
	}

	public int getage() {
		return this.age;
	}

	public String getId() {
		return this.id;
	}

	public String toString() {
		return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", age='" + this.age + '\'' + '}';
	}
}
