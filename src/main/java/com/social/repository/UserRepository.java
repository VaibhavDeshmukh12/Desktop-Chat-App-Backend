package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.fname LIKE %:query% OR u.lname LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(@Param("query") String query);
}
