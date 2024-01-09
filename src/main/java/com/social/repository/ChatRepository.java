package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.models.Chat;
import com.social.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{

	public List<Chat> findByUsersId(int id);
	
	@Query("Select c from Chat c where :user Member of c.users AND :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user")User user,@Param("reqUser") User reqUser);
}
