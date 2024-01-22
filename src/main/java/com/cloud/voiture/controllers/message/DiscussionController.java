package com.cloud.voiture.controllers.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.message.DiscussionService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private DiscussionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createDiscussion(@RequestBody Discussion discussion) throws Exception {
        try {
            return ResponseEntity.ok(new Response(service.addDiscussion(discussion), ""));

        } catch (Exception e) {
            return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getDiscussionsForUserWithUsers(@PathVariable int id) {
        try{
            return ResponseEntity.ok(new Response(utilisateurService.getDiscussionsForUserWithUsers(id), ""));
        }catch(Exception e){
            e.printStackTrace();
                  return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));

        }
    }
}
