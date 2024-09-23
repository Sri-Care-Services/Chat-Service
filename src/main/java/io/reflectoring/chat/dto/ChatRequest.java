package io.reflectoring.chat.dto;

public class ChatRequest {
    private String senderId;
    private String receiverId;

    // Getters and Setters

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
