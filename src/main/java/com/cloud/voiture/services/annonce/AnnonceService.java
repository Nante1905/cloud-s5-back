package com.cloud.voiture.services.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.repositories.annonce.AnnonceRepository;
import com.cloud.voiture.search.RechercheAnnonce;
import com.cloud.voiture.services.voiture.VoitureService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AnnonceService extends GenericService<Annonce> {

  @PersistenceContext
  private EntityManager entityManager;

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

  public List<Annonce> findComplex(RechercheAnnonce rechercheAnnonce) {
    return (List<Annonce>) entityManager
        .createNativeQuery("select * from annonce where id in (" + rechercheAnnonce.generateSql() + ")", Annonce.class)
        .getResultList();
  }

  @Override
  public Annonce update(Annonce model, int id) {
    if (model.getVoiture() != null) {
      model.setVoiture(voitureService.save(model.getVoiture()));
    }
    return super.update(model, id);
  }
}
