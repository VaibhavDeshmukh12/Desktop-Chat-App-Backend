package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.config.JwtProvider;
import com.social.exceptions.UserException;
import com.social.models.User;
import com.social.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setFname(user.getFname());
		newUser.setLname(user.getLname());
		newUser.setId(user.getId());
		newUser.setGender(user.getGender());

		User savedUser = this.userRepository.save(newUser);

		return savedUser;
	}

	@Override
	public User findUserById(int id) throws UserException {

		User user = this.userRepository.findById(id).get();
		if (user != null) {
			return user;
		}
		throw new UserException("User not exists with id: " + id);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = this.userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(int reqUserId, int id2) throws UserException {

		User reqUser = findUserById(reqUserId);

		User user2 = findUserById(id2);

		user2.getFollowers().add(reqUser.getId());

		reqUser.getFollowing().add(user2.getId());

		userRepository.save(reqUser);
		userRepository.save(user2);

		return reqUser;
	}

	@Override
	public User updateUSer(User user,int id) throws UserException {
		User user1 = this.userRepository.findById(id).get();
		
		if (user1 == null) {
			throw new UserException("User not found");
		}
		
		if (user.getEmail() != null) {
			user1.setEmail(user.getEmail());			
		}
		if (user.getFname()!=null) {
			user1.setFname(user.getFname());
		}
		
		if (user.getLname()!=null) {
			user1.setLname(user.getLname());
		}
		
		if (user.getPassword()!=null) {
			user1.setPassword(user.getPassword());			
		}
		if (user.getGender()!=null) {
			user1.setGender(user.getGender());			
		}
		
		User saavedUser =  this.userRepository.save(user1);
		
		return saavedUser;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {

		List<User> list = this.userRepository.searchUser(query);
		if (list.isEmpty()) {
			throw new UserException("User not found");
		}
		return list;
	}

	@Override
	public List<User> findAllUsers() {
		
		List<User> list = this.userRepository.findAll();
		
		return list;
	}

	@Override
	public String deleteUser(int id) throws UserException {
		
		User user = findUserById(id);
		
		if (user != null) {
			this.userRepository.delete(user);
			return "User Deleted Successfully with id: "+id;
		}
		
		throw new UserException("User not found!");
	}

	@Override
	public User findUserByJwt(String tokent) {
		String email = JwtProvider.getEmailFromJwtToken(tokent);
		
		User user = userRepository.findByEmail(email);
		
		return user;
	}

}
