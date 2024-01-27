package com.cloud.voiture.services.voiture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.voiture.Modele;
import com.cloud.voiture.repositories.voiture.ModeleRepo;

@Service
public class ModeleService extends GenericService<Modele> {
    @Autowired
    ModeleRepo modeleRepo;
    public List<Modele> findByMarque(int idMarque){
        return modeleRepo.getByMarque(idMarque);
    }
}
