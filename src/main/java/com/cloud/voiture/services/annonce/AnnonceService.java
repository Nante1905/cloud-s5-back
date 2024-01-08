package com.cloud.voiture.services.annonce;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.services.voiture.VoitureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnonceService extends GenericService<Annonce> {
    @Autowired
    private VoitureService voitureService;

    @Override
    public Annonce save(Annonce model) {
      model.setVoiture(voitureService.save(model.getVoiture()));
      return super.save(model);
    }
}
