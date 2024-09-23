package io.reflectoring.chat.controller;

import io.reflectoring.chat.dto.ChatDTO;
import io.reflectoring.chat.dto.ChatRequest;
import io.reflectoring.chat.dto.MessageRequest;
import io.reflectoring.chat.service.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private chatService chatServ;

    // Create a new chat
    @PostMapping("/create")
    public ResponseEntity<ChatDTO> createChat(@RequestBody ChatRequest chatRequest) {
        ChatDTO newChat = chatServ.createChat(chatRequest.getSenderId(), chatRequest.getReceiverId());
        return ResponseEntity.ok(newChat);
    }

    // Send a message in a chat
    @PostMapping("/{chatId}/send")
    public ResponseEntity<ChatDTO> sendMessage(@PathVariable String chatId, @RequestBody MessageRequest messageRequest) {
        ChatDTO updatedChat = chatServ.sendMessage(chatId, messageRequest.getSenderId(), messageRequest.getMessage());
        if (updatedChat != null) {
            return ResponseEntity.ok(updatedChat);
        }
        return ResponseEntity.notFound().build();
    }



    // Retrieve chat by ID
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatDTO> getChat(@PathVariable String chatId) {
        Optional<ChatDTO> chat = chatServ.getChatById(chatId);
        return chat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Get chat history for a user
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<ChatDTO>> getChatHistory(@PathVariable String userId) {
        List<ChatDTO> chats = chatServ.getChatsByUserId(userId);
        return ResponseEntity.ok(chats);
    }

}
