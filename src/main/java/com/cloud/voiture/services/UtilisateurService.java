package com.cloud.voiture.services;

import org.springframework.stereotype.Service;

import com.cloud.voiture.models.Utilisateur;

@Service
public class UtilisateurService {
    public Utilisateur findByEmailAndPassword(String email, String password) throws Exception {
        return null;
    }

    public Utilisateur findByEmail(String email) throws Exception {
        return null;
    }
}
