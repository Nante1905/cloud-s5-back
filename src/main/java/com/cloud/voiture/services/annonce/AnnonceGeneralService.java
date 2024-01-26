package com.cloud.voiture.services.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.annonce.AnnonceGeneral;
import com.cloud.voiture.repositories.annonce.AnnonceGeneralRepository;

@Service
public class AnnonceGeneralService extends GenericService<AnnonceGeneral> {
    @Autowired
    AnnonceGeneralRepository aGeneralRepository;
    private static int TAILLE_PAGE = 5;

    public List<AnnonceGeneral> findByIdUtilisateur(int idUtilisateur) {
        return aGeneralRepository.findByIdUtilisateur(idUtilisateur);
    }

    public List<AnnonceGeneral> findByIdUtilisateur(int idUtilisateur, int page, int taille) {
        if (page == 0) {
            return aGeneralRepository.findByIdUtilisateur(idUtilisateur);
        }
        if (taille == 0) {
            taille = TAILLE_PAGE;
        }
        return aGeneralRepository.findByIdUtilisateur(idUtilisateur, page, taille);
    }

    public List<AnnonceGeneral> findNonValide(int page, int taille) {
        if (page == 0) {
            return aGeneralRepository.findNonValide();
        }
        if (taille == 0) {
            taille = TAILLE_PAGE;
        }
        return aGeneralRepository.findNonValide(page, taille);
    }

    public List<AnnonceGeneral> findNonValideOf(int idUtilisateur, int page, int taille) {
        if (page == 0) {
            return aGeneralRepository.findNonValide(idUtilisateur);
        }
        if (taille == 0) {
            taille = TAILLE_PAGE;
        }
        return aGeneralRepository.findNonValide(idUtilisateur, page, taille);
    }

    public List<AnnonceGeneral> findValideOf(int idUtilisateur, int page, int taille) {
        if (page == 0) {
            return aGeneralRepository.findValide(idUtilisateur);
        }
        if (taille == 0) {
            taille = TAILLE_PAGE;
        }
        return aGeneralRepository.findValide(idUtilisateur, page, taille);
    }

    public List<AnnonceGeneral> findVenduOf(int idUtilisateur, int page, int taille) {
        if (page == 0) {
            return aGeneralRepository.findVendu(idUtilisateur);
        }
        if (taille == 0) {
            taille = TAILLE_PAGE;
        }
        return aGeneralRepository.findVendu(idUtilisateur, page, taille);
    }
}
