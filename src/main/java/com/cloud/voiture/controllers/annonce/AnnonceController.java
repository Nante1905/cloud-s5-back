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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.models.annonce.DTO.AnnonceDTO;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.voiture.EstimationPrix;
import com.cloud.voiture.models.voiture.Voiture;
import com.cloud.voiture.search.RechercheAnnonce;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.annonce.AnnonceGeneralService;
import com.cloud.voiture.services.annonce.AnnonceService;
import com.cloud.voiture.services.voiture.VoitureService;
import com.cloud.voiture.types.response.Response;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/annonces")
public class AnnonceController extends GenericController<Annonce> {

  @Autowired
  AnnonceService annonceService;

  @Autowired
  VoitureService voitureService;

  @Autowired
  UtilisateurService utilisateurService;

  @Autowired
  AnnonceGeneralService aGeneralService;

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<?> find(@PathVariable(name = "id") int id) {
    try {
      return ResponseEntity.ok(new Response(annonceService.findById(id), ""));
    } catch (NotFoundException e) {
      return ResponseEntity.status(404).body(new Response("Cette identifiant n'existe pas."));
    }
  }

  @GetMapping("/yours")
  public ResponseEntity<Response> getConnectedUserAnnonces() {
    try {
      List<AnnonceDTO> annonces = annonceService.findByUser();
      System.out.println(annonces.size());
      return ResponseEntity.ok(new Response(annonces, ""));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @GetMapping("{id}/historiques")
  public ResponseEntity<Response> getHistorique(@PathVariable(name = "id") int id) {
    try {
      return ResponseEntity.ok().body(new Response(annonceService.findHistorique(id), null));
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new Response("Il n'existe aucune annonce avec l'identifiant " + id));
    } catch (ValidationException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
    }
  }

  @PostMapping("/estimate")
  public ResponseEntity<Response> estimatePrice(@RequestBody Voiture voiture) {
    try {
      EstimationPrix estimationPrix = voitureService.estimate(voiture);
      return ResponseEntity.ok(new Response(estimationPrix, ""));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @Override
  @PostMapping
  public ResponseEntity<Response> save(@Valid @RequestBody Annonce annonce) {
    try {
      Utilisateur u = utilisateurService.getAuthenticated();
      annonce.setIdUtilisateur(u.getId());
      Annonce nouvelAnnonce = annonceService.save(annonce);
      return ResponseEntity.status(HttpStatus.CREATED).body(new Response(nouvelAnnonce, "Annonce créée"));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response("Aucun utilisateur connecté"));
    }
  }

  @GetMapping("/{id}/view")
  public ResponseEntity<Response> getByIdThenView(
      @PathVariable(name = "id") int id) {
    try {

      Utilisateur u = utilisateurService.getAuthenticated();
      try {
        annonceService.getByIdAndView(id, u.getId());
      } catch (Exception e) {
        System.out.println("deja vue");
      }
      return ResponseEntity.ok(
          new Response(annonceService.find(id), ""));
    } catch (AuthException e) {
      return ResponseEntity.status(403).body(new Response(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @PutMapping("{id}/valider")
  public ResponseEntity<Response> validerAnnonce(
      @PathVariable(name = "id") int id) {
    try {
      annonceService.valider(id);
      return ResponseEntity.ok().body(new Response(null, "Annonce validée"));
    } catch (NotFoundException e) {
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new Response(e.getMessage()));
    } catch (ValidationException e) {
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @PutMapping("{id}/refuser")
  public ResponseEntity<Response> refuserAnnonce(
      @PathVariable(name = "id") int id) {
    try {
      annonceService.refuser(id);
      return ResponseEntity.ok().body(new Response(null, "Annonce refusée"));
    } catch (NotFoundException e) {
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new Response(e.getMessage()));
    } catch (ValidationException e) {
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @GetMapping("/nonValide")
  public ResponseEntity<Response> findNonValide(@RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      List<AnnonceDTO> results = annonceService.getAllNonValide(page, taille);
      return ResponseEntity.ok(new Response(results, ""));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @PostMapping("/find")
  public ResponseEntity<Response> findComplex(
      @RequestBody RechercheAnnonce rechercheAnnonce, @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      List<AnnonceDTO> results = annonceService.findComplex(rechercheAnnonce, page, taille);
      return ResponseEntity.ok(new Response(results, ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @GetMapping("/test")
  public ResponseEntity<?> test(@RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int pageSize) {
    return ResponseEntity.ok(aGeneralService.findAll(page, pageSize));
  }
}
