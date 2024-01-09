package com.social.service;

import java.util.List;

import com.social.exceptions.StoryException;
import com.social.exceptions.UserException;
import com.social.models.Story;
import com.social.models.User;

public interface StoryService {

	public Story createStory(Story story,User user);
	
	public List<Story> findStoryByUserId(int id) throws StoryException, UserException;
}
