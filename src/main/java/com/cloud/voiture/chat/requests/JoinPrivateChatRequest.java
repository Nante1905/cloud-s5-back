package com.cloud.voiture.chat.requests;

public class JoinPrivateChatRequest {
    private String chatId;
    private int userId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter and setter methods...
}

