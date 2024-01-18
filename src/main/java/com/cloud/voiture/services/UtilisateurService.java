package com.cloud.voiture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.message.Message;
import com.cloud.voiture.repositories.auth.UtilisateurRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.auth.message.AuthException;

@Service
public class UtilisateurService extends GenericService<Utilisateur> {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Utilisateur findByEmailAndPassword(String email, String password) throws Exception {
        return this.utilisateurRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new Exception("Invalid credentials"));
    }

    public Utilisateur findByEmail(String email) throws NotFoundException {
        return this.utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<Utilisateur> getUtilisateurFromDiscussion(int expediteurId, int destinataireId) throws Exception {
        System.out.println(expediteurId + "and " + destinataireId);
        List<Utilisateur> utilisateurs = entityManager.createNativeQuery(
                "SELECT * FROM utilisateur WHERE id = :expediteurId OR id = :destinataireId",
                Utilisateur.class)
                .setParameter("expediteurId", expediteurId)
                .setParameter("destinataireId", destinataireId)
                .getResultList();
        if (utilisateurs == null || utilisateurs.size() < 2)
            throw new Exception("les utilisateurs n'existent peut etre pas");
        return utilisateurs;
    }

    public Utilisateur getAuthenticated() throws AuthException {
        Utilisateur u;
        try {
            u = this.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return u;
        } catch (NotFoundException e) {
            throw new AuthException("Aucun utilisateur connecté.");
        }
    }
}
