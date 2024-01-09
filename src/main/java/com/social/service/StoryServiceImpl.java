package com.social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.exceptions.StoryException;
import com.social.exceptions.UserException;
import com.social.models.Story;
import com.social.models.User;
import com.social.repository.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Story createStory(Story story, User user) {
		
		Story createdStory = new Story();
		createdStory.setCaptions(story.getCaptions());
		createdStory.setImage(story.getImage());
		createdStory.setTimestamp(LocalDateTime.now());
		createdStory.setUser(user);
				
		return this.storyRepository.save(createdStory);
	}

	@Override
	public List<Story> findStoryByUserId(int id) throws StoryException, UserException {
		
		User user = userService.findUserById(id);
		
		if (user == null) {
			throw new StoryException("User not found!");
		}
		
		return this.storyRepository.findByUserId(id);
	}

}
