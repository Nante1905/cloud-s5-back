package com.cloud.voiture.controllers.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.search.RechercheAnnonce;
import com.cloud.voiture.services.annonce.AnnonceService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/annonces")
public class AnnonceController extends GenericController<Annonce> {
  @Autowired
  AnnonceService annonceService;

  @PutMapping("{id}/valider")
  public ResponseEntity<Response> validerAnnonce(@PathVariable(name = "id") int id) {
    try {
      annonceService.valider(id);
      return ResponseEntity.ok().body(new Response(null, "Annonce validée"));
    } catch (NotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
    } catch (ValidationException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @PutMapping("{id}/refuser")
  public ResponseEntity<Response> refuserAnnonce(@PathVariable(name = "id") int id) {
    try {
      annonceService.refuser(id);
      return ResponseEntity.ok().body(new Response(null, "Annonce refusée"));
    } catch (NotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
    } catch (ValidationException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @GetMapping("/nonValide")
  public ResponseEntity<Response> findNonValide() {
    try {
      List<Annonce> results = annonceService.getAllNonValide();
      return ResponseEntity.ok(new Response(results, ""));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @PostMapping("/find")
  public ResponseEntity<Response> findComplex(@RequestBody RechercheAnnonce rechercheAnnonce) {
    try {
      List<Annonce> results = annonceService.findComplex(rechercheAnnonce);
      return ResponseEntity.ok(new Response(results, ""));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }
}