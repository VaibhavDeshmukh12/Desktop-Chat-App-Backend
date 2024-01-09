package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.exceptions.ChatException;
import com.social.exceptions.MessageExeption;
import com.social.models.Message;
import com.social.models.User;
import com.social.service.MessageService;
import com.social.service.UserService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestHeader("Authorization") String token, @RequestBody Message req,
			@PathVariable int chatId) throws MessageExeption, ChatException {
		User user = this.userService.findUserByJwt(token);
		Message message = this.messageService.createMessage(user, chatId, req);

		return message;
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatMessage(@RequestHeader("Authorization") String token,
			@PathVariable int chatId) throws MessageExeption, ChatException {
		@SuppressWarnings("unused")
		User user = this.userService.findUserByJwt(token);
		List<Message> messages = this.messageService.findChatsMessages(chatId);

		return messages;
	}
}
