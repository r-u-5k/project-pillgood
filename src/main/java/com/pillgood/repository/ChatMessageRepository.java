package com.pillgood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.ChatMessage;
import com.pillgood.entity.ChatRoom;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	List<ChatMessage> findByChatRoom(ChatRoom chatRoom);

}
