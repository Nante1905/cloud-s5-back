package com.cloud.voiture.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.auth.Utilisateur;

@RestController
@RequestMapping("utilisateurs")
public class UtilisateurController extends GenericController<Utilisateur> {

}
