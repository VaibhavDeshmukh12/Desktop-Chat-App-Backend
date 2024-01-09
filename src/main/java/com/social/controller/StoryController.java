package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Story;
import com.social.models.User;
import com.social.service.StoryService;
import com.social.service.UserService;

@RestController
public class StoryController {

	@Autowired
	private StoryService service;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story,@RequestHeader("Authorization") String token) {
		
		User user = this.userService.findUserByJwt(token);
		Story createdStory = this.service.createStory(story, user);		
		
		return createdStory;		
	}
	
	@GetMapping("/api/story/user/{id}")
	public List<Story> findStory(@PathVariable int id,@RequestHeader("Authorization") String token) throws Exception {
		
		@SuppressWarnings("unused")
		User user = this.userService.findUserByJwt(token);
		List<Story> createdStory = this.service.findStoryByUserId(id);		
		
		return createdStory;		
	}
}
