package com.Geekster.ChatApplication.dao;

import com.Geekster.ChatApplication.model.Chat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query(value = "Select * from chat_table where sender_user_id = :senderId and status_id = 1",
            nativeQuery = true)
    List<Chat> getChatByUserId(int senderId);

    @Query(value = "Select * from chat_table where (sender_user_id = :user1 and receiver_user_id = :user2)"+"or (sender_user_id = :user2 and receiver_user_id = :user1) and status_id = 1 order by created_date", nativeQuery = true)
    List<Chat> getAllConversations(int user1, int user2);

    @Modifying
    @Query(value = "Update chat_table set status_id = 2 where id = :chatId", countQuery = "SELECT count(*) FROM chat_table", nativeQuery = true)
    @Transactional
    public void deleteByID(int chatId);
}
