package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.exceptions.PostException;
import com.social.exceptions.UserException;
import com.social.models.Post;
import com.social.models.User;
import com.social.repository.PostRepository;
import com.social.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Post createPost(Post post, int userId) throws PostException, UserException {
		
		User user = this.userService.findUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		return this.postRepository.save(newPost);
	}

	@Override
	public String deletePost(int postId, int userId) throws PostException, UserException {
		
		Post post = findPostById(postId);
		
		User user = userService.findUserById(userId);
		
		if (post.getUser().getId() != user.getId()) {
			throw new PostException("You cant delete another user's post!");
		}
		this.postRepository.delete(post);
				
		return "Post Deleted Successfully";
	}

	@Override
	public List<Post> findPostByUserId(int userId) {
		return this.postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(int pid) throws PostException {
		Optional<Post> optional = this.postRepository.findById(pid);
		
		if (optional.isEmpty()) {
			throw new PostException("Post not found with pid: "+pid);
		}
		
		return optional.get();
	}

	@Override
	public List<Post> findAllPost() {
		return this.postRepository.findAll();
	}

	@Override
	public Post savedPost(int pid, int uid) throws PostException, UserException {
		
		Post post = findPostById(pid);
		
		User user = userService.findUserById(uid);
		
		if (user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		userRepository.save(user);		
		return post;
	}

	@Override
	public Post likePost(int pid, int uid) throws PostException, UserException {
		Post post = findPostById(pid);
		
		User user = userService.findUserById(uid);
		
		if (post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		
		
		return this.postRepository.save(post);
	}

}
