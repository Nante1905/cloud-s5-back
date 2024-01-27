package com.cloud.voiture.chat.requests;

import com.cloud.voiture.models.customPagination.CustomPagination;

public class JoinPrivateChatRequest {
    private String chatId;
    private CustomPagination pagination;
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public CustomPagination getPagination() {
        return pagination;
    }

    public void setPagination(CustomPagination pagination) {
        this.pagination = pagination;
    }
}