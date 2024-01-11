package com.cloud.voiture.controllers.voiture;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.voiture.Modele;

@RestController
@RequestMapping("/modeles")
public class ModeleController extends GenericController<Modele> {

}
