package com.cloud.voiture.controllers.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.types.response.Response;


@RequestMapping("/discussions")
@RestController
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UtilisateurService utilisateurService;
    @GetMapping
    public ResponseEntity<Response> getAllDiscussionAndLastMessages() {
        try {
            int iduser = utilisateurService.getAuthenticated().getId();
            return ResponseEntity.ok(new Response(utilisateurService.getDiscussionsForUserWithUsers(iduser),""));
        } catch (Exception e) {
            e.printStackTrace();
        return ResponseEntity
            .status(500)
            .body(new Response("Oups, une erreur s'est produite."));
            }
    }
    @PostMapping
    public ResponseEntity<Response> createDiscussion(@RequestBody CreatePrivateChatRequest request) {
        try {
            int iduser = utilisateurService.getAuthenticated().getId();
            return ResponseEntity.ok(new Response(discussionService.save(request, iduser),""));
        } catch (Exception e) {
            e.printStackTrace();
        return ResponseEntity
            .status(500)
            .body(new Response("Oups, une erreur s'est produite."));
            }
    }
}
