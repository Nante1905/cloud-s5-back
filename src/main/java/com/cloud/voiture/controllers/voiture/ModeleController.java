package com.cloud.voiture.controllers.voiture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.voiture.Modele;
import com.cloud.voiture.services.voiture.ModeleService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/modeles")
public class ModeleController extends GenericController<Modele> {
    @Autowired
    ModeleService service;
    @GetMapping("/fromMarque/{id}")
    public ResponseEntity<Response> findByMarque(@PathVariable("id") int id){
        try {
            return ResponseEntity.ok(new Response(service.findByMarque(id),""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
            .body(new Response("Oups, une erreur s'est produite."));
            }
        }
    }

