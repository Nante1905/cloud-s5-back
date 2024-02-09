package com.cloud.voiture.controllers.profil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.services.profil.ProfilService;
import com.cloud.voiture.types.response.Response;

@Secured({ "USER" })
@RestController
@RequestMapping("/profil")
public class ProfilController {
    @Autowired
    ProfilService profilService;
    @GetMapping
  public ResponseEntity<?> findAll(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
        return ResponseEntity.ok(new Response(profilService.getConnectedUserInfo(), ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

}
