package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.exceptions.ReelsException;
import com.social.exceptions.UserException;
import com.social.models.Reels;
import com.social.models.User;
import com.social.repository.ReelsRepository;


@Service
public class ReelsServiceimpl implements ReelsService{
	
	@Autowired
	private ReelsRepository reelsRepository;
	@Autowired
	private UserService userService;
	
	
	@Override
	public Reels createReels(Reels reels, User user) {
		
		Reels createReels = new Reels();
		createReels.setTitle(reels.getTitle());
		createReels.setUser(user);
		createReels.setVideo(reels.getVideo());
		
		return reelsRepository.save(createReels);
	}

	@Override
	public List<Reels> findAllReels() {
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReels(int uid) throws ReelsException, UserException {
		
		userService.findUserById(uid);		
		
		return reelsRepository.findByUserId(uid);
	}

}
