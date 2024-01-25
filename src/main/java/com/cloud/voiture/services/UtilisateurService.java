package com.cloud.voiture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.repositories.auth.UtilisateurRepository;
import com.cloud.voiture.services.message.DiscussionService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.auth.message.AuthException;

@Service
public class UtilisateurService extends GenericService<Utilisateur> {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DiscussionService discussionService;

    public Utilisateur findByEmailAndPassword(String email, String password) throws Exception {
        System.out.println("email " + email + " pwd " + password);
        return this.utilisateurRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new Exception("Identifiants incorrects. Réessayez."));
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

    public List<Discussion> getDiscussionsForUserWithUsers(int idutilisateur) throws Exception {
        // Utilisateur utilisateur = getAuthenticated();

        // List<Discussion> discussions =
        // discussionService.getDiscussionsForUser(utilisateur.getId());
        List<Discussion> discussions = discussionService.getDiscussionsForUser(idutilisateur);

        for (Discussion discussion : discussions) {
            int userId1 = discussion.getUserId1();
            int userId2 = discussion.getUserId2();

            Utilisateur user1 = findUtilisateurById(userId1);
            Utilisateur user2 = findUtilisateurById(userId2);

            discussion.setGauche(user1);
            discussion.setDroite(user2);
        }

        return discussions;
    }

    private Utilisateur findUtilisateurById(int userId) {
        return entityManager.find(Utilisateur.class, userId);
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
