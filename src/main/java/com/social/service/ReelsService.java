package com.social.service;

import java.util.List;

import com.social.exceptions.ReelsException;
import com.social.exceptions.UserException;
import com.social.models.Reels;
import com.social.models.User;

public interface ReelsService {
	
	public Reels createReels(Reels reels,User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(int uid) throws ReelsException, UserException;

}
