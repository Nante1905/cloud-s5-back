package com.cloud.voiture.controllers.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.models.message.Message;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.services.message.MessageService;
import com.cloud.voiture.types.response.Response;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService service;

    @Autowired
    private UtilisateurService serviceUser;

    @Autowired
    private DiscussionService serviceDiscussion;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  createMessage(@RequestBody Message message)throws Exception{
        try{
            return ResponseEntity.ok(new Response(service.addMessage(message), ""));
        }catch( Exception e ){
            return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
        }
    }

    @GetMapping
    public ResponseEntity<?>  getMessages() {
        try{
            return ResponseEntity.ok(new Response(service.findAllMessages(), ""));

        }catch( Exception e ){
            return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getMessageByUsers( @PathVariable String id ){
        try{
            List<Message> messages = service.findMessagesByidDiscussion(id);
            List<Utilisateur> utilisateurs = serviceDiscussion.findUsersByIdDiscussion(id);
            Discussion discussion = new Discussion( messages , utilisateurs.get(0) , utilisateurs.get(1) );

            return ResponseEntity.ok(new Response(discussion, ""));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));

        }
    }

    @GetMapping("/users/{expediteurId}/{destinataireId}")
    public ResponseEntity<?>  getMessagesByUsers(@PathVariable int expediteurId, @PathVariable int destinataireId) throws Exception{
        try{
            List<Message> messages = service.findMessagesByUsers(expediteurId, destinataireId);
            List<Utilisateur> utilisateurs = serviceUser.getUtilisateurFromDiscussion(expediteurId, destinataireId);
            Discussion discussion = new Discussion( messages , utilisateurs.get(0) , utilisateurs.get(1) );
            return ResponseEntity.ok(new Response(discussion, ""));
        }catch( Exception e ){
            return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
        }
    }

}
