package com.cloud.voiture.controllers.annonce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.crud.pagination.Paginated;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.models.annonce.DTO.AnnonceDTO;
import com.cloud.voiture.models.annonce.annoncePhoto.AnnoncePhoto;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.voiture.EstimationPrix;
import com.cloud.voiture.models.voiture.Voiture;
import com.cloud.voiture.search.RechercheAnnonce;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.annonce.AnnonceGeneralService;
import com.cloud.voiture.services.annonce.AnnonceService;
import com.cloud.voiture.services.media.MediaService;
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

  @Autowired
  private MediaService mediaService;

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Response> find(@PathVariable(name = "id") int id) {
    try {
      return ResponseEntity.ok(new Response(annonceService.findByIdAndAddView(id), ""));
    } catch (NotFoundException e) {
      return ResponseEntity.status(404).body(new Response("Cette identifiant n'existe pas."));
    }
  }

  @Override
  @GetMapping
  public ResponseEntity<?> findAll(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      System.out.println("Ato =================");
      if (page == 0 || taille == 0) {
        List<AnnonceDTO> results = annonceService.findAllAnnonce();
        return ResponseEntity.ok(new Response(results, ""));
      }
      Paginated<AnnonceDTO> result = annonceService.findAllAnnonces(page, taille);
      return ResponseEntity.ok(new Response(result, ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @Secured({ "USER" })
  @GetMapping("/supprime/moi")
  public ResponseEntity<Response> getAnnonceSupprimeOfConnectedUser(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      return ResponseEntity.ok()
          .body(new Response(annonceService.findDeletedAnnonceOfConnectedUser(page, taille), null));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new Response("Accès refusé.Veuillez vous connecter."));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @GetMapping("/nonValide/moi")
  public ResponseEntity<Response> getAnnonceNonValideOfConnectedUser(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      return ResponseEntity.ok()
          .body(new Response(annonceService.findAnnonceNonValideOfConnectedUser(page, taille), null));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new Response("Accès refusé.Veuillez vous connecter."));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @GetMapping("/valide/moi")
  public ResponseEntity<Response> getAnnonceValideOfConnectedUser(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      return ResponseEntity.ok()
          .body(new Response(annonceService.findAnnonceValideOfConnectedUser(page, taille), null));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new Response("Accès refusé.Veuillez vous connecter."));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @GetMapping("/vendu/moi")
  public ResponseEntity<Response> getAnnonceVenduOfConnectedUser(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      return ResponseEntity.ok()
          .body(new Response(annonceService.findAnnonceVenduOfConnectedUser(page, taille), null));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new Response("Accès refusé.Veuillez vous connecter."));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite"));
    }
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Response> delete(@PathVariable(name = "id") int id) {
    try {
      annonceService.deleteAnnonce(id);
      return ResponseEntity.status(HttpStatus.OK).body(new Response(null, "Annonce supprimée."));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new Response("Vous n'avez aucune annonce avec cette identifiant"));
    } catch (ValidationException e) {
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite."));
    }
  }

  @Secured({ "USER" })
  @PutMapping("/{id}/vendu")
  public ResponseEntity<Response> updateAsSold(@PathVariable(name = "id") int id) {
    try {
      annonceService.updateStatusToSold(id);
      return ResponseEntity.status(HttpStatus.OK).body(new Response(null, "Bravo, vous avez vendue une voiture."));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new Response("Vous n'avez aucune annonce avec cette identifiant"));
    } catch (ValidationException e) {
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Une erreur s'est produite."));
    }
  }

  @Secured({ "USER" })
  @GetMapping("/yours")
  public ResponseEntity<Response> getConnectedUserAnnonces(@RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      List<AnnonceDTO> annonces = annonceService.findByUser(page, taille);
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
    } catch (AuthException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Oups, une erreur s'est produite"));
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
    System.out.println("save annonce ");
    try {
      Utilisateur u = utilisateurService.getAuthenticated();
      annonce.setIdUtilisateur(u.getId());

      List<AnnoncePhoto> photos = new ArrayList<>();
      System.out.println(annonce.getMedias() + " =======================");
      if (annonce.getMedias() != null) {
        List<String> urls = mediaService.uploadMultipleFile(annonce.getMedias());
        for (String url : urls) {
          System.out.println(url);
          AnnoncePhoto photo = new AnnoncePhoto();
          photo.setAnnonce(annonce);
          photo.setUrl(url);
          photos.add(photo);
        }
      }
      annonce.setPhotos(photos);

      Annonce nouvelAnnonce = annonceService.save(annonce);
      return ResponseEntity.status(HttpStatus.CREATED).body(new Response(nouvelAnnonce, "Annonce créée"));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(new Response("Accès refusé.Veuillez vous connecter."));
    } catch (Exception e) {
      e.printStackTrace();
      try {
        this.mediaService.deleteMediaFiles(annonce.getMedias());
      } catch (Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new Response("Une erreur s'est produite: impossible de supprimer les photos"));
      }
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response("Une erreur s'est produite: Vérifiez votre type de données"));
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

  @Secured({ "ADMIN" })
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

  @Secured({ "ADMIN" })
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

  @Secured({ "ADMIN" })
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
      @RequestBody RechercheAnnonce rechercheAnnonce,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "0") int taille) {
    try {
      List<AnnonceDTO> results = annonceService.findComplex(rechercheAnnonce, page, taille);
      return ResponseEntity.ok(new Response(results, ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }

  @Secured({ "USER" })
  @PutMapping("/{id}/toggle_favoris")
  public ResponseEntity<Response> markAsFavori(@PathVariable(name = "id") int id) {
    try {
      int status = annonceService.toggleFavori(id);
      String message = "Annonce ajoutée aux favoris.";
      if (status == -1) {
        message = "Annonce supprimée des favoris";
      }

      return ResponseEntity.ok(new Response(null, message));
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response(e.getMessage()));
    } catch (NotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
          .body(new Response("Annonce invalide: impossible de mettre en favori."));
    } catch (ValidationException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new Response(e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(new Response("Une erreur s'est produite"));
    }
  }

  // @PutMapping("/{id}")
  // public ResponseEntity<?> update(
  // @RequestBody @Valid Annonce model,
  // @PathVariable(name = "id") int id) {
  // try {
  // Annonce results = annonceService.update(model, id);
  // return ResponseEntity.ok(new Response(results, "Modifié avec succes"));

  // } catch (DataIntegrityViolationException e) {
  // // e.printStackTrace();
  // System.out.println(e.getMessage());
  // if (e.getCause() instanceof ConstraintViolationException) {
  // ConstraintViolationException sqlException = (ConstraintViolationException)
  // e.getCause();
  // String sqlState = sqlException.getSQLState();
  // String columnName = Utilities.extractColumnName(
  // Annonce.class,
  // sqlException.getMessage());
  // System.out.println(sqlException.getMessage());
  // System.out.println(columnName + "==================");
  // return ResponseEntity.status(HttpStatus.BAD_REQUEST)
  // .body(new Response(
  // SqlErrorMessage.getMessage(sqlState, columnName,
  // "Annonce")));
  // }
  // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
  // new Response("Contrainte de donnée violée"));

  // } catch (Exception e) {
  // e.printStackTrace();
  // return ResponseEntity.status(500).body(new Response("Erreur interne du
  // serveur"));
  // }
  // }
}
