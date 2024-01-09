package com.social.service;

import com.social.exceptions.CommentException;
import com.social.exceptions.PostException;
import com.social.exceptions.UserException;
import com.social.models.Comment;

public interface CommentService {

	public Comment createComment(Comment comment,int pid,int uid) throws CommentException, UserException, PostException;
	
	public Comment findCommentById(int id) throws CommentException;
	
	public Comment likeComment(int cid,int uid) throws CommentException, UserException;
}
