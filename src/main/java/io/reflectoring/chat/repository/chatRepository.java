package io.reflectoring.chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import io.reflectoring.chat.entity.Chat;

import java.util.List;

@Repository
public interface chatRepository extends MongoRepository<Chat, String> {
    List<Chat> findBySenderIdOrReceiverId(String senderId, String receiverId);
}

