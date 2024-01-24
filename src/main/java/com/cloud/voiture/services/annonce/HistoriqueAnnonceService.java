package com.cloud.voiture.services.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.config.Constant;
import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.annonce.HistoriqueAnnonce;
import com.cloud.voiture.repositories.annonce.HistoriqueAnnonceRepo;

@Service
public class HistoriqueAnnonceService extends GenericService<HistoriqueAnnonce> {
    @Autowired
    Constant config;

    @Autowired
    HistoriqueAnnonceRepo historiqueRepo;

    public List<HistoriqueAnnonce> findByIdAnnonce(int idAnnonce) {
        return historiqueRepo.findByIdAnnonce(idAnnonce);
    }

    public String getEtat(HistoriqueAnnonce h) throws ValidationException {
        if (h.getStatus() == config.getAnnonceCree()) {
            return "Création";
        }
        if (h.getStatus() == config.getAnnonceRefuse()) {
            return "Refus par l'admin";
        }
        if (h.getStatus() == config.getAnnonceValide()) {
            return "Validation";
        }
        if (h.getStatus() == config.getAnnonceVendu()) {
            return "Vendue";
        }
        if (h.getStatus() == -10) {
            return "Supprimée";
        }
        throw new ValidationException("Status d'annonce inconnu");
    }
}
