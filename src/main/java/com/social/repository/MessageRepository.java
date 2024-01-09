package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.social.models.Message;

public interface MessageRepository extends JpaRepository<Message,Integer>{

	@Query("SELECT m FROM Message m WHERE m.chat.chat_id=:chatId")
	public List<Message> findByChatId(int chatId);
}
