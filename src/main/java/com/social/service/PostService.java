package com.social.service;

import java.util.List;

import com.social.exceptions.PostException;
import com.social.exceptions.UserException;
import com.social.models.Post;

public interface PostService {
	
	Post createPost(Post post,int userId) throws PostException, UserException;

	String deletePost(int postId,int userId) throws PostException, UserException;
	
	List<Post> findPostByUserId(int userId);
	
	Post findPostById(int pid) throws PostException;
	
	List<Post> findAllPost();
	
	Post savedPost(int pid,int uid) throws PostException, UserException;
	
	Post likePost(int pid,int uid) throws PostException, UserException;
}
