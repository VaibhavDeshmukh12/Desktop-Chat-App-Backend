package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.social.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	@Query("SELECT p FROM Post p WHERE p.user.id=:uid")
	List<Post> findPostByUserId(int uid);
}
