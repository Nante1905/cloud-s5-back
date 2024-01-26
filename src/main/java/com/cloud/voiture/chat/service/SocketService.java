package com.cloud.voiture.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.chat.requests.ChatMessageRequest;
import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.chat.requests.JoinPrivateChatRequest;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.services.message.MessageService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

@Service
public class SocketService {

    @Autowired
    private  MessageService messageService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UtilisateurService utilisateurService;
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
        // Message storedMessage = messageService.addMessage(message);
        sendSocketmessage(server, message);
    }



}
