package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Comment;
import com.social.models.User;
import com.social.service.CommentService;
import com.social.service.UserService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;

	@PostMapping("/api/comments/post/{pid}")
	public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,
			@PathVariable int pid) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Comment Createdcomment = commentService.createComment(comment, pid, user.getId());
		return Createdcomment;
	}

	@PutMapping("/api/comments/like/{cid}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,
			@PathVariable int cid) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Comment likedcomment = commentService.likeComment(cid, user.getId());
		return likedcomment;
	}

}
