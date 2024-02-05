package com.cloud.voiture.chat.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.voiture.chat.requests.ChatMessageRequest;
import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.chat.requests.JoinPrivateChatRequest;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.notification.NotificationPush;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.services.notification.NotificationPushService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.google.firebase.messaging.FirebaseMessagingException;

import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SocketService {
    @Autowired
    NotificationPushService notifPushService;

    @Autowired
    DiscussionService discussionService;

    @Autowired
    UtilisateurService utilisateurService;

    public void sendSocketmessage(SocketIOServer server, SocketIOClient sender, ChatMessageRequest message)
            throws AuthException, NotFoundException {
        server.getRoomOperations(message.getDiscussionId()).getClients().stream().forEach(client -> {
            if (client.getSessionId().compareTo(sender.getSessionId()) != 0) {
                message.setIdExpedit(message.getIdExpedit());
                client.sendEvent("message_received", message);
                client.sendEvent("new_message", message);
                log.info("client send event to " + client.getRemoteAddress().toString());
            }
        });
        // sending notification

        Utilisateur to = new Utilisateur();
        List<Integer> discussionUsers = this.discussionService.findUsersByIdDiscussion(message.getDiscussionId());
        for (int u : discussionUsers) {
            if (u != message.getIdExpedit()) {
                to = this.utilisateurService.find(u);
            }
        }

        List<String> tokens = notifPushService.getTokenOf(to.getId());
        NotificationPush notif = new NotificationPush(to.getPrenom() + " " + to.getNom(),
                message.getMessage(),
                tokens);
        try {
            if (tokens.size() > 0) {
                notifPushService.sendNotif(notif);
            }
            System.out.println("notif envoyé");
        } catch (FirebaseMessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println("==========================================");
            System.out
                    .println("Erreur lors de l'envoi de notification message to:" + to.getPrenom()
                            + " " + e.getMessage());
            System.out.println("==============================================");
        }
    }

    public void createDiscussion(SocketIOServer server, SocketIOClient client, CreatePrivateChatRequest data)
            throws Exception {

        if (data != null) {
            client.joinRoom(data.getDiscussionId());
            server.getRoomOperations(data.getDiscussionId()).sendEvent("created", data);
        } else {
            // Handle the case where data is null
            System.out.println("data is null");
        }
    }

    public void joinDiscussion(SocketIOClient client, JoinPrivateChatRequest request) {
        client.joinRoom(request.getChatId());
    }

    public void saveMessage(SocketIOServer server, SocketIOClient sender, ChatMessageRequest message) throws Exception {
        sendSocketmessage(server, sender, message);
    }

}
