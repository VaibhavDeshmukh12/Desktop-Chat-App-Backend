package com.social.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.exceptions.CommentException;
import com.social.exceptions.PostException;
import com.social.exceptions.UserException;
import com.social.models.Comment;
import com.social.models.Post;
import com.social.models.User;
import com.social.repository.CommentRepository;
import com.social.repository.PostRepository;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment createComment(Comment comment, int pid, int uid) throws UserException, PostException {
		
		User user = userService.findUserById(uid);
		
		Post post = postService.findPostById(pid);
				
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		Comment savedComment = commentRepository.save(comment);
		post.getComments().add(savedComment);
		
		postRepository.save(post);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(int id) throws CommentException {
		
		Optional<Comment> optional = commentRepository.findById(id);
		
		if (optional.isEmpty()) {
			throw new CommentException("Comment not exist!");
		}
		
		return optional.get();
	}

	@Override
	public Comment likeComment(int cid, int uid) throws CommentException, UserException {
		
		Comment comment = findCommentById(cid);
		User user = userService.findUserById(uid);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else comment.getLiked().remove(user);
		
		return commentRepository.save(comment);
	}

}
