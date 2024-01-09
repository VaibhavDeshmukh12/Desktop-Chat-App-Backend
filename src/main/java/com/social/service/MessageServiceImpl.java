package com.social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.exceptions.ChatException;
import com.social.exceptions.MessageExeption;
import com.social.models.Chat;
import com.social.models.Message;
import com.social.models.User;
import com.social.repository.ChatRepository;
import com.social.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messageRepository;	
	@Autowired
	private ChatService chatService;
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Message createMessage(User user, int chatId, Message req) throws MessageExeption, ChatException {
		Message message = new Message();
		Chat chat = this.chatService.findChatById(chatId);
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message savedMessage = this.messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(int chatId) throws MessageExeption, ChatException {
		@SuppressWarnings("unused")
		Chat chat = this.chatService.findChatById(chatId);
		return this.messageRepository.findByChatId(chatId);
	}
}
