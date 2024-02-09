package com.cloud.voiture.controllers.message;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.chat.exceptions.UnauthorizedChatting;
import com.cloud.voiture.chat.requests.ChatMessageRequest;
import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.chat.requests.JoinPrivateChatRequest;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.models.message.Message;
import com.cloud.voiture.models.notification.NotificationPush;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.services.message.MessageService;
import com.cloud.voiture.services.notification.NotificationPushService;
import com.cloud.voiture.types.response.Response;
import com.google.firebase.messaging.FirebaseMessagingException;

import jakarta.security.auth.message.AuthException;

@Secured({ "USER" })
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private NotificationPushService notifPushService;

    @PostMapping
    public ResponseEntity<Response> getMessages(@RequestBody JoinPrivateChatRequest request) {
        try {
            // int iduser = 1;
            int iduser = utilisateurService.getAuthenticated().getId();
            if (discussionService.allowed(request.getChatId(), iduser)) {
                return ResponseEntity
                        .ok(new Response(messageService.findMessagesByidDiscussion(request.getChatId(),
                                request.getPagination().getNumero(), request.getPagination().getTaillePage(),
                                request.getExtraSkip()), ""));
            } else {
                throw new UnauthorizedChatting();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(new Response("Oups, une erreur s'est produite."));
        }
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Response> sendMessage(@RequestBody ChatMessageRequest messageRequest) {
        try {

            Message message = messageService.addMessage(messageRequest);
            // sending notification

            Utilisateur sender = utilisateurService.getAuthenticated();
            Utilisateur to = new Utilisateur();
            List<Integer> discussionUsers = this.discussionService
                    .findUsersByIdDiscussion(messageRequest.getDiscussionId());
            for (int u : discussionUsers) {
                if (u != sender.getId()) {
                    to = this.utilisateurService.find(u);
                }
            }

            List<String> tokens = notifPushService.getTokenOf(to.getId());
            NotificationPush notif = new NotificationPush(sender.getPrenom() + " " + sender.getNom(),
                    message.getContenu(),
                    tokens);
            System.out.println("tokens: " + tokens);
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
            return ResponseEntity.ok(new Response(message, ""));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new Response("Une erreur s'est produite"));
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<Response> contact(@RequestBody CreatePrivateChatRequest request) {
        try {
            // int iduser = 1;
            int iduser = utilisateurService.getAuthenticated().getId();
            Discussion discussion = discussionService.save(request, iduser);
            return ResponseEntity.ok(new Response(discussion, ""));
        } catch (ValidationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response(e.getMessage()));
        } catch (AuthException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response("une erreur s'est produite"));
        }
    }
}