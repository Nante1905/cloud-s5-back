package com.cloud.voiture.services.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.notification.NotificationPush;
import com.cloud.voiture.models.notification.UtilisateurToken;
import com.cloud.voiture.repositories.notification.UtilisateurTokenRepository;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificationPushService {
    @Autowired
    UtilisateurTokenRepository tokenRepository;

    public BatchResponse sendNotif(NotificationPush notifToSend)
            throws FirebaseMessagingException, InterruptedException, ExecutionException {
        Notification notification = Notification.builder().setTitle(notifToSend.getTitre())
                .setBody(notifToSend.getContenu()).build();
        List<Message> messages = new ArrayList<>();
        for (String token : notifToSend.getToken()) {
            messages.add(Message.builder().setToken(token).setNotification(notification).build());
        }

        return FirebaseMessaging.getInstance().sendEach(messages);
    }

    public List<String> getTokenOf(int idUtilisateur) {
        List<UtilisateurToken> uTokens = tokenRepository.findByIdUtilisateur(idUtilisateur);
        return uTokens.stream().map(UtilisateurToken::getToken).collect(Collectors.toList());

    }
}
