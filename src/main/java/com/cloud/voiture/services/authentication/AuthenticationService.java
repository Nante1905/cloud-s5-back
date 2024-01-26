package com.cloud.voiture.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.auth.Utilisateur;
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
            System.out.println("role " + user.getRole().getReference());
            if (user.getRole().getReference().equals("USER")) {
                return jwtManager.generateToken(user,
                        this.customUserDetailsService.getAuthorities(user));
            }
            throw new Exception("Identifiants incorrects. Réessayez.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String loginBackOffice(String email, String password) throws Exception {

        try {
            Utilisateur user = utilisateurService.findByEmailAndPassword(email, password);
            System.out.println("role " + user.getRole().getReference());
            if (user.getRole().getReference().equals("ADMIN")) {
                return jwtManager.generateToken(user, this.customUserDetailsService.getAuthorities(user));
            }
            throw new Exception("Identifiants incorrects. Réessayez.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void register(Utilisateur utilisateur) throws Exception {
        try {
            this.utilisateurService.save(utilisateur);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
