package com.cloud.voiture.chat.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.voiture.models.message.Discussion;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class CreateDiscussionHandler implements DataListener<Discussion> {

    @Autowired
    private SocketIOServer socketIoServer;
    @Override
    public void onData(SocketIOClient client, Discussion data, AckRequest ackSender) throws Exception {
        client.joinRoom(data.getIdDiscussion());
        socketIoServer.getRoomOperations(data.getIdDiscussion()).sendEvent("contacted", data);
    }
}
