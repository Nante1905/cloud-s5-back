package com.cloud.voiture.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.repositories.auth.UtilisateurRepository;

@Service
public class UtilisateurService extends GenericService<Utilisateur> {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findByEmailAndPassword(String email, String password) throws Exception {
        return this.utilisateurRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new Exception("Invalid credentials"));
    }

    public Utilisateur findByEmail(String email) throws Exception {
        return this.utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Utilisateur not found"));
    }
}
