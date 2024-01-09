package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.models.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>{

	public List<Story> findByUserId(int uid);
}
