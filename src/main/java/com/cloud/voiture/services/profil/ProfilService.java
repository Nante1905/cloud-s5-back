package com.cloud.voiture.services.profil;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.repositories.auth.UtilisateurRepository;
import com.cloud.voiture.services.UtilisateurService;

import jakarta.security.auth.message.AuthException;

@Service
public class ProfilService {
    @Autowired
    UtilisateurService utilisateurService;
    
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur getConnectedUserInfo() throws Exception{
        
        Optional<Utilisateur> user = utilisateurRepository.findById(utilisateurService.getAuthenticated().getId());
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("Utilisateur invalide");
    }

}
