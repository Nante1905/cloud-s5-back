package com.cloud.voiture.chat.requests;

public class ChatMessageRequest {

    private String message;
    private String discussionId;

    public ChatMessageRequest() {
    }

    public ChatMessageRequest(String discussionId, String message) {
        super();
        this.discussionId = discussionId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "userName='" + discussionId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

   
}
