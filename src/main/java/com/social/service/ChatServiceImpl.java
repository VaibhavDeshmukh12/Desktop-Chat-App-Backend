package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.exceptions.ChatException;
import com.social.models.Chat;
import com.social.models.User;
import com.social.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(User reqUser, User user2) {
		
		Chat isExist = this.chatRepository.findChatByUsersId(user2, reqUser);
		
		if (isExist!=null) {
			return isExist;
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		
		return this.chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(int chatId) throws ChatException {
		Optional<Chat> opt = chatRepository.findById(chatId);
		if(opt.isEmpty()) {
			throw new ChatException("Chat Not Found With ChatId: "+chatId);
		}
		
		return opt.get();
	}

	@Override
	public List<Chat> findByUsersChat(int uid) {
		return this.chatRepository.findByUsersId(uid);
	}

}
