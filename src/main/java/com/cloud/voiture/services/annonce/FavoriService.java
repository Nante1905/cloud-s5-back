package com.cloud.voiture.services.annonce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.annonce.favori.Favori;
import com.cloud.voiture.models.annonce.favori.FavoriAnnonceID;
import com.cloud.voiture.repositories.annonce.FavoriRepository;

@Service
public class FavoriService {
    @Autowired
    FavoriRepository favoriRepository;

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
