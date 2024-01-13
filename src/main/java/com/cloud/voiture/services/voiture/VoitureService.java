package com.cloud.voiture.services.voiture;

import com.cloud.voiture.config.Constant;
import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.voiture.EstimationPrix;
import com.cloud.voiture.models.voiture.Voiture;
import com.cloud.voiture.repositories.voiture.VoitureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoitureService extends GenericService<Voiture> {

  @Autowired
  private Constant params;

  @Autowired
  private VoitureRepo voitureRepo;

  public EstimationPrix estimate(Voiture voiture) {
    double prix = voitureRepo.estimate(
      voiture.getIdModele(),
      voiture.getEtat() - params.getMargeEtat(),
      voiture.getEtat() + params.getMargeEtat(),
      voiture.getIdEnergie(),
      voiture.getIdBoiteVitesse()
    );
    EstimationPrix es = new EstimationPrix();
    es.setPrix(prix);
    es.setVoiture(voiture);
    return es;
  }
}
