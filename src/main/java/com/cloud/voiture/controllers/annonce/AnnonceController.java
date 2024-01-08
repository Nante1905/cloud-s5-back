package com.cloud.voiture.controllers.annonce;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.models.voiture.Categorie;
@RestController
@RequestMapping("/api/annonces")
public class AnnonceController extends GenericController<Annonce>{
    
}
