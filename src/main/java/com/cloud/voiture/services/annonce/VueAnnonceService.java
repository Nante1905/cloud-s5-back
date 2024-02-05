package com.cloud.voiture.services.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.annonce.VueAnnonce;
import com.cloud.voiture.repositories.annonce.VueAnnonceRepository;

@Service
public class VueAnnonceService extends GenericService<VueAnnonce> {
    @Autowired
    VueAnnonceRepository vRepository;

    List<VueAnnonce> findByIdUtilisateurAndIdAnnonce(int idUtilisateur, int idAnnonce) {
        return vRepository.findByIdUtilisateurAndIdAnnonce(idUtilisateur, idAnnonce);
    }
}
