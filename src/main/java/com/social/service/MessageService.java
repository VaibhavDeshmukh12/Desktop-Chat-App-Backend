package com.social.service;

import java.util.List;

import com.social.exceptions.ChatException;
import com.social.exceptions.MessageExeption;
import com.social.models.Message;
import com.social.models.User;

public interface MessageService {

	public Message createMessage(User user,int chatId,Message req) throws MessageExeption, ChatException;
	
	public List<Message> findChatsMessages(int chatId) throws MessageExeption, ChatException;
}
