package com.cloud.voiture.services.annonce;

import com.cloud.voiture.config.Constant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.annonce.Annonce;

import com.cloud.voiture.models.annonce.HistoriqueAnnonce;
import com.cloud.voiture.repositories.annonce.AnnonceRepository;
import com.cloud.voiture.services.voiture.VoitureService;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AnnonceService extends GenericService<Annonce> {
  @Autowired
  private VoitureService voitureService;
  @Autowired
  private AnnonceRepository annonceRepo;
  @Autowired
  private HistoriqueAnnonceService historiqueService;
  @Autowired
  private Constant params;

  @Transactional(rollbackOn = Exception.class)
  public void valider(int idAnnonce) throws NotFoundException, ValidationException {
    Annonce a = this.find(idAnnonce);
    checkValidation(a);

    HistoriqueAnnonce historique = new HistoriqueAnnonce();
    historique.setIdAnnonce(idAnnonce);
    historique.setDateMaj(LocalDateTime.now());
    historique.setStatus(params.getAnnonceValide());

    updateStatus(idAnnonce, params.getAnnonceValide());
    historiqueService.save(historique);
  }

  @Transactional
  public void refuser(int idAnnonce) throws NotFoundException, ValidationException {
    Annonce a = this.find(idAnnonce);
    checkValidation(a);

    HistoriqueAnnonce historique = new HistoriqueAnnonce();
    historique.setIdAnnonce(idAnnonce);
    historique.setDateMaj(LocalDateTime.now());
    historique.setStatus(params.getAnnonceRefuse());

    updateStatus(idAnnonce, params.getAnnonceRefuse());
    historiqueService.save(historique);

  }

  public void checkValidation(Annonce a) throws ValidationException {
    if (a.getStatus() != params.getAnnonceCree()) {
      if (a.getStatus() == params.getAnnonceValide()) {
        throw new ValidationException("Impossible de modifier le status de cette annonce. Elle est déjà validée");
      }
      if (a.getStatus() == -params.getAnnonceRefuse()) {
        throw new ValidationException("Impossible de modifier le status de cette annonce. Elle est déjà refusée");
      }
      if (a.getStatus() == params.getAnnonceVendu()) {
        throw new ValidationException("Impossible de modifier le status de cette annonce. Elle est déjà vendue");
      }
      throw new ValidationException("Impossible de modifier le status de cette annonce. Status inconnu");

    }
  }

  @Transactional
  public void updateStatus(int idAnnonce, int status) {
    annonceRepo.updateStatus(idAnnonce, status);

  }

  @Override
  public Annonce save(Annonce model) {
    model.setVoiture(voitureService.save(model.getVoiture()));
    return super.save(model);


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
