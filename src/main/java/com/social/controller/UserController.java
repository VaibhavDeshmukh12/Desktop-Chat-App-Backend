package com.social.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.exceptions.UserException;
import com.social.models.User;
import com.social.service.UserService;

@RestController
public class UserController {
		
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		List<User> users = new ArrayList<>();
		
		users = this.userService.findAllUsers();
		
		return users;
	}
	
	@GetMapping("/api/users/{id}")
	public User getUserByID(@PathVariable("id") int id) throws UserException {
		
		User user = this.userService.findUserById(id);
		
		return user;	
		
	}
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String token,@RequestBody User user) throws UserException {
		
		User reqUser = userService.findUserByJwt(token);
		
		User user2 = this.userService.updateUSer(user, reqUser.getId());
		return user2;
		
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserhandler(@RequestHeader("Authorization") String token,@PathVariable int userId2) throws UserException {
		
		User reqUser = userService.findUserByJwt(token);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query) throws UserException{
		List<User> users = userService.searchUser(query);
		return users;
	}
	
	@DeleteMapping("/api/users/{id}")
	public String deleteUser(@PathVariable int id) throws UserException {
		String string = this.userService.deleteUser(id);
		return string;		
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String token) {
		
		User user = userService.findUserByJwt(token);
//		System.out.println(token);
		user.setPassword(null);
		return user;
	}
}
