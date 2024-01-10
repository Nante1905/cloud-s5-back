package com.cloud.voiture.services.annonce;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.repositories.annonce.AnnonceRepository;
import com.cloud.voiture.services.voiture.VoitureService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AnnonceService extends GenericService<Annonce> {

  @Autowired
  private VoitureService voitureService;

  @Autowired
  private AnnonceRepository annonceRepository;

  public List<Annonce> getAllNonValide() {
    return annonceRepository.getAllNonValide();
  }

  @Override
  public Annonce save(Annonce model) {
    model.setVoiture(voitureService.save(model.getVoiture()));
    return super.save(model);
  }

  @Override
  public Annonce update(Annonce model, int id)  {
    if(model.getVoiture()!=null){
      model.setVoiture(voitureService.save(model.getVoiture()));
    }
    return super.update(model, id);
  }
}
