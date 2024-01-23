package com.cloud.voiture.services.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.annonce.AnnonceEtFavori;
import com.cloud.voiture.models.annonce.favori.Favori;
import com.cloud.voiture.models.annonce.favori.FavoriAnnonceID;
import com.cloud.voiture.repositories.annonce.FavoriRepository;
import com.cloud.voiture.services.UtilisateurService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.auth.message.AuthException;

@Service
public class FavoriService {
    @Autowired
    FavoriRepository favoriRepository;

    @Autowired
    UtilisateurService utilisateurService;
    @PersistenceContext
    EntityManager entityManager;

    public List<AnnonceEtFavori> findFavoriOf(int idUtilisateur) throws AuthException {

        List<AnnonceEtFavori> res = entityManager.createNativeQuery(
                "select a.*, f.date_ajout from v_annonce_gen_valide a  join annonce_favori f on a.id = f.id_annonce and f.id_utilisateur = :user",
                AnnonceEtFavori.class).setParameter("user", idUtilisateur).getResultList();
        return res;

    }

    public Favori findByAnnonceAndUtilisateur(int idAnnonce, int idUtilisateur) {
        return favoriRepository.findById(new FavoriAnnonceID(idUtilisateur, idAnnonce)).orElse(null);
    }

    public Favori existOrLiked(int idUtilisateur, int idAnnonce) throws NotFoundException {
        System.out.println(idUtilisateur + "==================== " + idAnnonce);

        return favoriRepository.existsOrLiked(idUtilisateur, idAnnonce)
                .orElseThrow(() -> new NotFoundException());
    }

    public void save(Favori f) {
        favoriRepository.insert(f.getIdUtilisateur(), f.getIdAnnonce());
    }

    public void delete(Favori f) {
        favoriRepository.delete(f);
    }
}
