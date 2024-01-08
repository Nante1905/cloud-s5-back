package com.cloud.voiture.controllers.annonce;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.services.annonce.AnnonceService;
import com.cloud.voiture.types.response.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annonces")
public class AnnonceController extends GenericController<Annonce> {

  @Autowired
  AnnonceService annonceService;

  @GetMapping("/nonValide")
  public ResponseEntity<Response> findNonValide() {
    try {
      List<Annonce> results = annonceService.getAllNonValide();
      return ResponseEntity.ok(new Response(results, ""));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }
}
