package com.social.service;

import java.util.List;

import com.social.exceptions.UserException;
import com.social.models.User;

public interface UserService {

	public User registerUser(User user);
	
	public List<User> findAllUsers();
	
	public User findUserById(int id) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(int id1,int id2) throws UserException;
	
	public User updateUSer(User user,int id) throws UserException;
	
	public List<User> searchUser(String query) throws UserException;
	
	public String deleteUser(int id) throws UserException;
	
	public User findUserByJwt(String tokent);
}
