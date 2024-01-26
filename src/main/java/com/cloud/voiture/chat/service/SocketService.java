package com.cloud.voiture.chat.service;

import org.springframework.stereotype.Service;

import com.cloud.voiture.chat.requests.ChatMessageRequest;
import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.chat.requests.JoinPrivateChatRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

@Service
public class SocketService {
    public void sendSocketmessage(SocketIOServer server, ChatMessageRequest message) {
        server.getRoomOperations(message.getDiscussionId()).sendEvent("message_received", message);
    }
    public void createDiscussion(SocketIOServer server, SocketIOClient client, CreatePrivateChatRequest data) throws Exception {
        
        if (data != null) {
            client.joinRoom(data.getDiscussionId());
            server.getRoomOperations(data.getDiscussionId()).sendEvent("created", data);
        } else {
            // Handle the case where data is null
            System.out.println("data is null");
        }
    }
    
    public void joinDiscussion(SocketIOClient client, JoinPrivateChatRequest request){
        client.joinRoom(request.getChatId());
    }
    public void saveMessage(SocketIOServer server, ChatMessageRequest message) throws Exception {
        sendSocketmessage(server, message);
    }



}
