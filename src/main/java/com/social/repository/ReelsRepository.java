package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer>{
	
	public List<Reels> findByUserId(int id);

}
