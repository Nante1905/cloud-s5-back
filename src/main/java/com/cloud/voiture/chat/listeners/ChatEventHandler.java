package com.cloud.voiture.chat.listeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloud.voiture.models.message.Message;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

// ChatEventHandler.java
@Component
public class ChatEventHandler implements DataListener<Message> {

    @Autowired
    private SocketIOServer socketIoServer;

    @Override
    public void onData(SocketIOClient client, Message message, AckRequest ackRequest) {
        String channelId = message.getIdDiscussion();
        socketIoServer.getRoomOperations(channelId).sendEvent("messageReceived", message);
    }
}
