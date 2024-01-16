package com.cloud.voiture.services.annonce;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.voiture.config.Constant;
import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.models.annonce.HistoriqueAnnonce;
import com.cloud.voiture.models.annonce.HistoriqueAnnonceDTO;
import com.cloud.voiture.models.annonce.HistoriqueAnnonceMin;
import com.cloud.voiture.models.annonce.VueAnnonce;
import com.cloud.voiture.models.annonce.annoncePhoto.AnnoncePhoto;
import com.cloud.voiture.repositories.annonce.AnnonceRepository;
import com.cloud.voiture.search.RechercheAnnonce;
import com.cloud.voiture.services.voiture.VoitureService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class AnnonceService extends GenericService<Annonce> {

  @Autowired
  private VueAnnonceService vueAnnonceService;
  @Autowired
  private CommissionService commissionService;
  @Autowired
  private VoitureService voitureService;
  @Autowired
  private HistoriqueAnnonceService historiqueService;

  @Autowired
  private Constant params;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private AnnonceRepository annonceRepository;

  @Autowired
  Constant config;

  private static int TAILLE_PAGE = 10;

  @Transactional
  public void getByIdAndView(int idAnnonce, int iduser) throws Exception {
    VueAnnonce vueAnnonce = new VueAnnonce();
    vueAnnonce.setIdUtilisateur(iduser);
    vueAnnonce.setIdAnnonce(idAnnonce);
    try {
      vueAnnonceService.save(vueAnnonce);
      annonceRepository.addView(idAnnonce);
    } catch (DataIntegrityViolationException e) {
      System.out.println("deja vu");
    }
  }

  public HistoriqueAnnonceDTO findHistorique(int idAnnonce) throws NotFoundException, ValidationException {
    Annonce annonce = find(idAnnonce);
    List<HistoriqueAnnonce> historiques = historiqueService.findByIdAnnonce(idAnnonce);

    List<HistoriqueAnnonceMin> historiqueMin = new ArrayList<HistoriqueAnnonceMin>();

    for (HistoriqueAnnonce histo : historiques) {
      HistoriqueAnnonceMin m = new HistoriqueAnnonceMin();
      m.setDate(histo.getDateMaj());
      m.setStatus(historiqueService.getEtat(histo));
      historiqueMin.add(m);
    }
    return new HistoriqueAnnonceDTO(annonce, historiqueMin);
  }

  public List<Annonce> findByUser(int idUser) {
    System.out.println(idUser);
    return annonceRepository.findByUser(idUser);
  }

  @Transactional(rollbackOn = Exception.class)
  public void valider(int idAnnonce)
      throws NotFoundException, ValidationException {
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
  public void refuser(int idAnnonce)
      throws NotFoundException, ValidationException {
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
        throw new ValidationException(
            "Impossible de modifier le status de cette annonce. Elle est déjà validée");
      }
      if (a.getStatus() == params.getAnnonceRefuse()) {
        throw new ValidationException(
            "Impossible de modifier le status de cette annonce. Elle est déjà refusée");
      }
      if (a.getStatus() == params.getAnnonceVendu()) {
        throw new ValidationException(
            "Impossible de modifier le status de cette annonce. Elle est déjà vendue");
      }
      throw new ValidationException(
          "Impossible de modifier le status de cette annonce. Status inconnu");
    }
  }

  @Transactional
  public void updateStatus(int idAnnonce, int status) {
    annonceRepository.updateStatus(idAnnonce, status);
  }

  @Override
  @Transactional(rollbackOn = Exception.class)
  public Annonce save(Annonce model) {

    model.generateReference(annonceRepository.getNumOfTheDay(), params);
    System.out.println(model.getReference());
    model.setVoiture(voitureService.save(model.getVoiture()));
    System.out.println(model.getVoiture().getId());
    model.setIdVoiture(model.getVoiture().getId());
    model.defineCommission(commissionService.getLast());

    if (model.getPhotos() != null) {
      for (AnnoncePhoto photo : model.getPhotos()) {
        photo.setAnnonce(model);
      }
    }

    model = super.save(model);
    HistoriqueAnnonce historiqueAnnonce = new HistoriqueAnnonce();
    historiqueAnnonce.setIdAnnonce(model.getId());
    historiqueAnnonce.setDateMaj(LocalDateTime.now());
    historiqueAnnonce.setStatus(params.getAnnonceCree());
    historiqueService.save(historiqueAnnonce);

    return model;
  }

  public List<Annonce> getAllNonValide(int page) {
    if (page == 0) {
      return annonceRepository.getAllNonValide();
    }
    return annonceRepository.getAllNonValide(page, TAILLE_PAGE);
  }

  public List<Annonce> getAllNonValide() {
    return annonceRepository.getAllNonValide();
  }

  public List<Annonce> findComplex(RechercheAnnonce rechercheAnnonce) {
    return (List<Annonce>) entityManager
        .createNativeQuery(
            "select * from annonce where id in (" +
                rechercheAnnonce.generateSql() +
                ")",
            Annonce.class)
        .getResultList();
  }

  @Override
  @Transactional(rollbackOn = Exception.class)
  public Annonce update(Annonce model, int id) {
    if (model.getVoiture() != null) {
      model.setVoiture(voitureService.save(model.getVoiture()));
    }
    return super.update(model, id);
  }
}
