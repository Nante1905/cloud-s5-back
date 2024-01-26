package com.cloud.voiture.controllers.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/me")
    public ResponseEntity<?> getDiscussionOfConnected() throws Exception {
        return ResponseEntity.ok(new Response(utilisateurService.getDiscussionsForUserWithUsers(1), ""));
    }
}
