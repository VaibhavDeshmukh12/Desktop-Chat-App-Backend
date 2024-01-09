package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.exceptions.ChatException;
import com.social.exceptions.UserException;
import com.social.models.Chat;
import com.social.models.User;
import com.social.request.CreateChatRequest;
import com.social.service.ChatService;
import com.social.service.UserService;

@RestController
public class ChatController {
	@Autowired
	private ChatService chatService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String token,@RequestBody CreateChatRequest request) throws ChatException, UserException {
		User reqUser = this.userService.findUserByJwt(token);
		User user = this.userService.findUserById(request.getUserId());
		Chat chat = chatService.createChat(reqUser,user);
		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String token) {
		User user = this.userService.findUserByJwt(token);
		List<Chat> chat = chatService.findByUsersChat(user.getId());
		return chat;
	}
}
