package com.dennis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.dennis.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	@Query("{ 'id' : ?0 }")
	Optional<User> findById(String id);

	@Query("{ 'age' : ?0 }")
	List<User> findByAge(int age);

	@Query("{ 'name' : ?0 }")
	List<User> findByName(String name);
}
