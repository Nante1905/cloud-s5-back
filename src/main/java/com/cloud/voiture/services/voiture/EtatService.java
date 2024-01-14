package com.cloud.voiture.services.voiture;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.voiture.Etat;
import com.cloud.voiture.repositories.voiture.EtatRepo;

@Service
public class EtatService extends GenericService<Etat> {
    // @Autowired
    // EtatRepo etatRepo;

    // @Override
    // public Etat save(Etat model) {
    // try {
    // etatRepo.save(model);
    // } catch (DataIntegrityViolationException d) {
    // // TODO: handle exception
    // }
    // }
}
