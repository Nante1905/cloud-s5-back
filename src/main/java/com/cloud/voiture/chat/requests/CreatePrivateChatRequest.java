package com.cloud.voiture.chat.requests;

public class CreatePrivateChatRequest {
    private int targetUserId;
    private String discussionId;
    public int getTargetUserId() {
        return targetUserId;
    }
    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }
    public String getDiscussionId() {
        return discussionId;
    }
    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    } 
}
