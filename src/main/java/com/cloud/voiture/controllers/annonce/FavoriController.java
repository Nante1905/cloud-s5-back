package com.cloud.voiture.controllers.annonce;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.services.annonce.AnnonceService;
import com.cloud.voiture.types.response.Response;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("/favoris")
public class FavoriController {
    @Autowired
    AnnonceService annonceService;

    @GetMapping
    public ResponseEntity<Response> findAll(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "0") int taille) {
        try {
            return ResponseEntity.ok()
                    .body(new Response(annonceService.findFavoriOfAuthenticatedUser(page, taille), null));
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(new Response(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new Response("Une erreur s'est produite."));
        }
    }
}
