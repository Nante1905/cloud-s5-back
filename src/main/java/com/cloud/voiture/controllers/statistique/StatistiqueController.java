package com.cloud.voiture.controllers.statistique;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.models.statistique.StatRequest;
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

    @PostMapping("/benefice")
    public ResponseEntity<?> getBeneficeStats(@RequestBody StatRequest params) {
        try {
            HashMap<String, Object> data = statService.getBeneficeStatistique(params, entityManager);
            return ResponseEntity.ok(new Response(data, ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response("Oups, une erreur s'est produite."));
        }

    }
}
