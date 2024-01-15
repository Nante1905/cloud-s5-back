package com.cloud.voiture.controllers.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.models.message.Message;
import com.cloud.voiture.services.UtilisateurService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createMessage(@RequestBody Message message)throws Exception{
        Response result = new Response("");
        try{
            result.setData(service.addMessage(message));
            result.setOK(true);
        }catch( Exception e ){
            result.setErr(e.getMessage());
            result.setOK(false);
        }
        return result;
    }

    @GetMapping
    public Response getMessages() {
        Response result = new Response("");
        try{
            result.setData(service.findAllMessages());
            result.setOK(true);
        }catch( Exception e ){
            result.setErr(e.getMessage());
            result.setOK(false);
        }
        return result;
    }

    @GetMapping("/users/{expediteurId}/{destinataireId}")
    public Response getMessagesByUsers(@PathVariable int expediteurId, @PathVariable int destinataireId) throws Exception{
        Response result = new Response("");
        try{
            List<Message> messages = service.findMessagesByUsers(expediteurId, destinataireId);

            List<Utilisateur> utilisateurs = serviceUser.getUtilisateurFromDiscussion(expediteurId, destinataireId);

            Discussion discussion = new Discussion( messages , utilisateurs.get(0) , utilisateurs.get(1) );
            result.setData(discussion);
            result.setOK(true);

            return result;
        }catch( Exception e ){
            result.setErr(e.getMessage());
            result.setOK(false);
        }
        return result;
    }

}
