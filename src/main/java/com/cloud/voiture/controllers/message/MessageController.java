package com.cloud.voiture.controllers.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.chat.exceptions.UnauthorizedChatting;
import com.cloud.voiture.chat.requests.ChatMessageRequest;
import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.chat.requests.JoinPrivateChatRequest;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.models.message.Message;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.services.message.MessageService;
import com.cloud.voiture.types.response.Response;

// TODO: uncomment this line
// @Secured({"USER"})
@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService service;

    // @Autowired
    // private UtilisateurService utilisateurService;
    // @Autowired
    // private ChatEventHandler chatEventHandler;
    @Autowired
    private DiscussionService serviceDiscussion;
    // @Autowired
    // private SocketIOClient client;
    // @Autowired
    // private CreateDiscussionHandler discussionHandler;
    // @Autowired
    // private JoinChatHandler joinChatHandler;
    // @Autowired
    // private LeaveRoomHandler leaveRoomHandler;

    @PostMapping("/sendMessage")
    public ResponseEntity<Response> sendMessage(@RequestBody ChatMessageRequest messageRequest) {
        try {

            Message message = service.addMessage(messageRequest);
            // chatEventHandler.onData(client, message, null);
            return ResponseEntity.ok(new Response(message, ""));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new Response(e.getMessage()));
        }
    }

    @PostMapping("/leave")
    public ResponseEntity<Response> leaveDiscussion(@RequestBody JoinPrivateChatRequest request) {
        try {
            int iduser = 1;
            // TODO: remove this line
            // int iduser = utilisateurService.getAuthenticated().getId();
            // leaveRoomHandler.onData(client, request, null);
            return ResponseEntity.ok(new Response(null, ""));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new Response("Oups, une erreur s'est produite."));
        }
    }

    @PostMapping("/join")
    public ResponseEntity<Response> joinDiscussion(@RequestBody JoinPrivateChatRequest request) {
        try {
            int iduser = 1;
            // TODO: remove this line
            // int iduser = utilisateurService.getAuthenticated().getId();
            if (serviceDiscussion.allowed(request.getChatId(), iduser) == false) {
                throw new UnauthorizedChatting();
            }
            request.setUserId(iduser);
            List<Message> messages = service.findMessagesByidDiscussion(request.getChatId());
            List<Utilisateur> utilisateurs = serviceDiscussion.findUsersByIdDiscussion(request.getChatId());
            // joinChatHandler.onData(client, request, null);
            Discussion discussion = new Discussion(messages, utilisateurs.get(0), utilisateurs.get(1));

            return ResponseEntity.ok(new Response(discussion, ""));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new Response("Oups, une erreur s'est produite."));
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<Response> contact(@RequestBody CreatePrivateChatRequest request) {
        try {
            int iduser = 1;
            // TODO: remove this line
            // int iduser = utilisateurService.getAuthenticated().getId();
            Discussion discussion = serviceDiscussion.save(request, iduser);
            // discussionHandler.onData(client, discussion, null);
            return ResponseEntity.ok(new Response(service.findAllMessages(), ""));

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageByUsers(@PathVariable String id) {
        try {
            List<Message> messages = service.findMessagesByidDiscussion(id);
            List<Utilisateur> utilisateurs = serviceDiscussion.findUsersByIdDiscussion(id);
            Discussion discussion = new Discussion(messages, utilisateurs.get(0), utilisateurs.get(1));

            return ResponseEntity.ok(new Response(discussion, ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(new Response("Oups, une erreur s'est produite."));

        }
    }
}
