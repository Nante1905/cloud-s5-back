package com.cloud.voiture.controllers.statistique;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.models.statistique.MarqueBenefice;
import com.cloud.voiture.models.statistique.StatRequest;
import com.cloud.voiture.services.stats.StatistiqueService;
import com.cloud.voiture.types.response.Response;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.RequestMapping;

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
            HashMap<String, Object> data = new HashMap<>() {
                {
                    put("benefice", statService.getBeneficeParMois(params.getMois(), params.getAnnee()));
                    put("beneficeMarque", statService.findBeneficeParMarque(params, entityManager));
                };
            };

            return ResponseEntity.ok(new Response(data, ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response("Oups, une erreur s'est produite."));
        }

    }
}
