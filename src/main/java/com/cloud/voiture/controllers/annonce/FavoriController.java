package com.cloud.voiture.controllers.annonce;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.annonce.Favori;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favoris")
public class FavoriController extends GenericController<Favori> {}
