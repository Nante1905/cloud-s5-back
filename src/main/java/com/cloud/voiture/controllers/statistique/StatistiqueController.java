package com.cloud.voiture.controllers.statistique;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.models.statistique.StatInscription;
import com.cloud.voiture.models.statistique.StatRequest;
import com.cloud.voiture.models.statistique.StatTopSeller;
import com.cloud.voiture.models.statistique.StatTopSellerRequest;
import com.cloud.voiture.services.stats.StatistiqueService;
import com.cloud.voiture.types.response.Response;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/stats")
public class StatistiqueController {

  @Autowired
  StatistiqueService statService;

  @PersistenceContext
  EntityManager entityManager;

  @PostMapping("/topSellers")
  public ResponseEntity<?> getTopSellerStat(
      @RequestBody StatTopSellerRequest params) {
    try {
      List<StatTopSeller> stat = statService.getTopSellers(params);
      return ResponseEntity.ok(new Response(stat, ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
    }
  }

  @PostMapping("/inscriptions")
  public ResponseEntity<?> getInscriptionsParMois(
      @RequestBody StatRequest request) {
    try {
      List<StatInscription> stat = statService.getInscriptionsParMois(request);

      return ResponseEntity.ok(new Response(stat, ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
    }
  }

  @PostMapping("/benefice")
  public ResponseEntity<?> getBeneficeStats(@RequestBody StatRequest params) {
    try {
      HashMap<String, Object> data = statService.getBeneficeStatistique(
          params,
          entityManager);
      return ResponseEntity.ok(new Response(data, ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
    }
  }

  @GetMapping("/general")
  public ResponseEntity<?> getGeneralStats(@RequestBody StatRequest params) {
    try {

      List<HashMap<String, Object>> beneficeParMois = this.statService.getBeneficeParMois(params.getMois(),
          params.getAnnee());
      int nbVendu = this.statService.getNbVendu(params.getMois(), params.getAnnee());
      int nbAnnonce = this.statService.getNbAnnonce(params.getMois(), params.getAnnee());
      int avgCreationVente = this.statService.getAvgCreationVente(params.getMois(), params.getAnnee());

      HashMap<String, Object> data = new HashMap<String, Object>();
      data.put("beneficeParMois", beneficeParMois);
      data.put("nbVendu", nbVendu);
      data.put("nbAnnonce", nbAnnonce);
      data.put("avgCreationVente", avgCreationVente);

      return ResponseEntity.ok(new Response(data, ""));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity
          .status(500)
          .body(new Response("Oups, une erreur s'est produite."));
    }
  }
}
