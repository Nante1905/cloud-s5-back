package com.cloud.voiture.controllers.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.chat.exceptions.UnauthorizedChatting;
import com.cloud.voiture.chat.requests.ChatMessageRequest;
import com.cloud.voiture.chat.requests.JoinPrivateChatRequest;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.services.message.MessageService;
import com.cloud.voiture.types.response.Response;


@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping 
    public ResponseEntity<Response> getMessages(@RequestBody JoinPrivateChatRequest request) {
        try {
            int iduser = 1;
            // TODO remove this line 
            // int iduser = utilisateurService.getAuthenticated().getId();
            if(discussionService.allowed(request.getChatId(), iduser)){
                return ResponseEntity.ok(new Response(messageService.findMessagesByidDiscussion(request.getChatId()), ""));
            }
            else{
                throw new UnauthorizedChatting();
            }
        } catch (Exception e) {
            e.printStackTrace();
      return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
        }
    }
    @PostMapping("/send")
    public ResponseEntity<Response> sendMessage(@RequestBody ChatMessageRequest request) {
        try {
            int iduser = 1;
            // TODO remove this line 
            // int iduser = utilisateurService.getAuthenticated().getId();
            if(discussionService.allowed(request.getDiscussionId(), iduser)){
                return ResponseEntity.ok(new Response(messageService.addMessage(request), ""));
            }
            else{
                throw new UnauthorizedChatting();
            }
        } catch (Exception e) {
            e.printStackTrace();
      return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
        }
    }
}