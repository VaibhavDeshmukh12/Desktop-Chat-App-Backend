package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.exceptions.ReelsException;
import com.social.exceptions.UserException;
import com.social.models.Reels;
import com.social.models.User;
import com.social.service.ReelsService;
import com.social.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	private ReelsService reelsService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reels,@RequestHeader("Authorization")String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);		
		Reels createdReels = reelsService.createReels(reels, reqUser);
		return createdReels;				
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
				
		List<Reels> reels = reelsService.findAllReels();
		return reels;				
	}
	
	@GetMapping("/api/reels/user/{uid}")
	public List<Reels> findReels(@PathVariable int uid) throws ReelsException, UserException {
				
		List<Reels> reels = reelsService.findUsersReels(uid);
		return reels;				
	}
}
