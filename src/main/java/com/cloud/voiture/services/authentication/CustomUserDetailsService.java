package com.cloud.voiture.services.authentication;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.Utilisateur;
import com.cloud.voiture.services.UtilisateurService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur;
        try {
            utilisateur = this.utilisateurService.findByEmail(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        }
        // TODO : change new array list to fun getAuthorities
        return new User(utilisateur.getEmail(), "", new ArrayList<GrantedAuthority>());
    }

    // public Collection<? extends GrantedAuthority> getAuthorities(Employe emp) {
    // List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    // authorities.add(new SimpleGrantedAuthority("EMP"));
    // authorities.add(new SimpleGrantedAuthority(emp.getDirection().getCode()));
    // authorities.add(new SimpleGrantedAuthority(emp.getCodePoste()));
    // authorities.add(new SimpleGrantedAuthority(emp.getDirection().getCode() + " "
    // + emp.getCodePoste()));
    // return authorities;
    // }

}
