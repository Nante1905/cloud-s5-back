package com.cloud.voiture.services.authentication;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.Utilisateur;
import com.cloud.voiture.services.UtilisateurService;

@Service
public class AuthenticationService {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTManager jwtManager;

    public String login(String email, String password) throws Exception {
        try {
            Utilisateur user = utilisateurService.findByEmailAndPassword(email, password);
            // TODO : change new array list to fun getAuthorities
            return jwtManager.generateToken(user, new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
