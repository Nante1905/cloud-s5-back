package com.cloud.voiture.controllers.voiture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.voiture.Categorie;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategorieController extends GenericController<Categorie> {

}
