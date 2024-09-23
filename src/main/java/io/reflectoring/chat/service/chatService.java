package io.reflectoring.chat.service;

import io.reflectoring.chat.dto.ChatDTO;
import io.reflectoring.chat.dto.MessageDTO;
import io.reflectoring.chat.entity.Chat;
import io.reflectoring.chat.entity.Message;
import io.reflectoring.chat.repository.chatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class chatService {

    @Autowired
    private chatRepository chatRepo;

    // Create a new chat
    public ChatDTO createChat(String senderId, String receiverId) {
        Chat chat = new Chat();
        chat.setSenderId(senderId);
        chat.setReceiverId(receiverId);
        Chat savedChat = chatRepo.save(chat);
        return mapToDTO(savedChat);
    }

    // Send a message
    public ChatDTO sendMessage(String chatId, String senderId, String messageText) {
        Optional<Chat> chatOptional = chatRepo.findById(chatId);
        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();
            Message message = new Message();
            message.setMessageId("message_" + System.currentTimeMillis());
            message.setSenderId(senderId);
            message.setMessage(messageText);
            message.setTimestamp(LocalDateTime.now());

            chat.getMessages().add(message);
            Chat updatedChat = chatRepo.save(chat);
            return mapToDTO(updatedChat);
        }
        return null;
    }

    // Get chat by ID
    public Optional<ChatDTO> getChatById(String chatId) {
        Optional<Chat> chat = chatRepo.findById(chatId);
        return chat.map(this::mapToDTO);
    }

    // View chat history for a user
    public List<ChatDTO> getChatsByUserId(String userId) {
        Iterable<Chat> chats = chatRepo.findBySenderIdOrReceiverId(userId, userId);
        return ((List<Chat>) chats).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Mapping from Chat to ChatDTO
    private ChatDTO mapToDTO(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setChatId(chat.getChatId());
        chatDTO.setSenderId(chat.getSenderId());
        chatDTO.setReceiverId(chat.getReceiverId());
        chatDTO.setMessages(chat.getMessages().stream().map(this::mapMessageToDTO).collect(Collectors.toList()));
        return chatDTO;
    }

    // Mapping from Message to MessageDTO
    private MessageDTO mapMessageToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setSenderId(message.getSenderId());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setTimestamp(message.getTimestamp());
        return messageDTO;
    }
}
