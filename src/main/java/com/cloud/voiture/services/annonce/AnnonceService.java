package com.cloud.voiture.services.annonce;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cloud.voiture.config.ApplicationTimeZone;
import com.cloud.voiture.config.Constant;
import com.cloud.voiture.crud.pagination.Paginated;
import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.models.annonce.AnnonceEtFavori;
import com.cloud.voiture.models.annonce.AnnonceGeneral;
import com.cloud.voiture.models.annonce.HistoriqueAnnonce;
import com.cloud.voiture.models.annonce.HistoriqueAnnonceDTO;
import com.cloud.voiture.models.annonce.HistoriqueAnnonceMin;
import com.cloud.voiture.models.annonce.VueAnnonce;
import com.cloud.voiture.models.annonce.DTO.AnnonceDTO;
import com.cloud.voiture.models.annonce.annoncePhoto.AnnoncePhoto;
import com.cloud.voiture.models.annonce.favori.Favori;
import com.cloud.voiture.models.annonce.favori.FavoriAnnonceID;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.notification.NotificationPush;
import com.cloud.voiture.repositories.annonce.AnnonceGeneralRepository;
import com.cloud.voiture.repositories.annonce.AnnonceRepository;
import com.cloud.voiture.search.RechercheAnnonce;
import com.cloud.voiture.services.UtilisateurService;
import com.cloud.voiture.services.notification.NotificationPushService;
import com.cloud.voiture.services.voiture.VoitureService;
import com.google.firebase.messaging.FirebaseMessagingException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.security.auth.message.AuthException;
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
  private UtilisateurService utilisateurService;

  @Autowired
  Constant config;

  @Autowired
  AnnonceGeneralService aGeneralService;
  @Autowired
  AnnonceGeneralRepository aGeneralRepository;

  @Autowired
  FavoriService favoriService;
  @Autowired
  NotificationPushService notifPushService;

  public Paginated<AnnonceDTO> findAllAnnonces(int page, int taille) {
    Pageable pageable = PageRequest.of(page - 1, taille);
    Page<AnnonceGeneral> pagination = aGeneralRepository.findAll(pageable);
    List<AnnonceDTO> annonces = new ArrayList<>();
    for (AnnonceGeneral a : pagination.getContent()) {
      AnnonceDTO dto = new AnnonceDTO(a);
      dto.setPhotos(findPhotos(a.getId()));
      annonces.add(dto);
    }
    return new Paginated<AnnonceDTO>(
        annonces,
        pagination.getTotalPages(),
        pagination.getNumber() + 1);
  }

  public List<AnnonceDTO> findAllAnnonce() {
    List<AnnonceGeneral> aG = aGeneralService.findAll();
    List<AnnonceDTO> annonces = new ArrayList<>();
    for (AnnonceGeneral a : aG) {
      AnnonceDTO dto = new AnnonceDTO(a);
      dto.setPhotos(findPhotos(a.getId()));
      annonces.add(dto);
    }
    return annonces;
  }

  public List<AnnonceDTO> findFavoriOfAuthenticatedUser(int page, int taille) throws AuthException {
    Utilisateur u = utilisateurService.getAuthenticated();
    List<AnnonceEtFavori> res = favoriService.findFavoriOf(u.getId(), page, taille);
    List<AnnonceDTO> a = new ArrayList<>();
    for (AnnonceEtFavori annonce : res) {
      AnnonceDTO dto = new AnnonceDTO(annonce);
      dto.setPhotos(findPhotos(annonce.getId()));
      a.add(dto);
    }
    return a;
  }

  @Transactional
  public int toggleFavori(int idAnnonce) throws AuthException, NotFoundException, ValidationException {
    Utilisateur u = utilisateurService.getAuthenticated();
    AnnonceEtFavori favori = favoriService.existOrLiked(u.getId(), idAnnonce);
    Favori f = new Favori();
    f.setId(new FavoriAnnonceID(u.getId(), idAnnonce));

    if (favori.getIdUtilisateur() == u.getId()) {
      throw new ValidationException("Vous ne pouvez pas mettre votre propre annonce en favori.");
    }
    if (favori.getDateAjout() == null) {
      if (favori.getStatus() != 5) {
        throw new ValidationException("Seule les annonces disponibles peuvent être mises en favori.");
      } else {
        favoriService.save(f);
      }
      return 1;
    } else {
      System.out.println("delete ohhhh " + favori.getIdUtilisateur() + " a " +
          favori.getIdUtilisateur());
      favoriService.delete(f);
      return -1;
    }
  }

  @Transactional
  public void deleteAnnonce(int idAnnonce) throws NotFoundException, ValidationException, AuthException {
    Utilisateur u = utilisateurService.getAuthenticated();
    Annonce a = findById(idAnnonce);
    if (a.getUtilisateur().getId() != u.getId()) {
      throw new ValidationException("Erreur: vous n'avez aucune annonce avec cette identifiant.");
    }
    HistoriqueAnnonce histo = new HistoriqueAnnonce();
    histo.setIdAnnonce(a.getId());
    histo.setStatus(config.getAnnonceSupprime());
    historiqueService.save(histo);
    updateStatus(idAnnonce, config.getAnnonceSupprime());
  }

  @Transactional
  public void updateStatusToSold(int idAnnonce) throws NotFoundException, ValidationException, AuthException {
    Utilisateur u = utilisateurService.getAuthenticated();
    Annonce a = findById(idAnnonce);
    if (a.getUtilisateur().getId() != u.getId()) {
      throw new ValidationException("Erreur: vous n'avez aucune annonce avec cette identifiant.");
    }
    if (a.getStatus() != config.getAnnonceValide()) {
      throw new ValidationException(
          "Erreur: seules les annonces validées et non vendues peuvent être classées comme vendues");
    }
    HistoriqueAnnonce histo = new HistoriqueAnnonce();
    histo.setIdAnnonce(a.getId());
    histo.setStatus(config.getAnnonceVendu());
    historiqueService.save(histo);
    updateStatus(idAnnonce, config.getAnnonceVendu());
  }

  public List<AnnonceDTO> findAnnonceNonValideOfConnectedUser(int page, int taille) throws AuthException {
    Utilisateur u = utilisateurService.getAuthenticated();
    List<AnnonceGeneral> aG = aGeneralService.findNonValideOf(u.getId(), page, taille);
    List<AnnonceDTO> annonces = new ArrayList<>();
    for (AnnonceGeneral a : aG) {
      AnnonceDTO dto = new AnnonceDTO(a);
      dto.setPhotos(findPhotos(a.getId()));
      annonces.add(dto);
    }
    return annonces;
  }

  public List<AnnonceDTO> findAnnonceValideOfConnectedUser(int page, int taille) throws AuthException {
    Utilisateur u = utilisateurService.getAuthenticated();
    List<AnnonceGeneral> aG = aGeneralService.findValideOf(u.getId(), page, taille);
    List<AnnonceDTO> annonces = new ArrayList<>();
    for (AnnonceGeneral a : aG) {
      AnnonceDTO dto = new AnnonceDTO(a);
      dto.setPhotos(findPhotos(a.getId()));
      annonces.add(dto);
    }
    return annonces;
  }

  public List<AnnonceDTO> findAnnonceVenduOfConnectedUser(int page, int taille) throws AuthException {
    Utilisateur u = utilisateurService.getAuthenticated();
    List<AnnonceGeneral> aG = aGeneralService.findVenduOf(u.getId(), page, taille);
    List<AnnonceDTO> annonces = new ArrayList<>();
    for (AnnonceGeneral a : aG) {
      AnnonceDTO dto = new AnnonceDTO(a);
      dto.setPhotos(findPhotos(a.getId()));
      annonces.add(dto);
    }
    return annonces;
  }

  public List<AnnoncePhoto> findPhotos(int idAnnonce) {
    return annonceRepository.getPhotos(idAnnonce);
  }

  // TODO
  // Na tsy connecté ary ve lay user d comptabilisena hoe vues?
  @Transactional(rollbackOn = { Exception.class })
  public Annonce findById(int idAnnonce) throws NotFoundException {
    System.out.println("maka détails annonce");
    Utilisateur u = new Utilisateur();
    u.setId(0);
    try {
      u = utilisateurService.getAuthenticated();
    } catch (AuthException e) {
      System.out.println("details sans connexion");
    }
    try {
      if (u.getRole() == null || u.getRole().getReference().equals("ADMIN") == false) {
        if (u.getId() == 0) {
          getByIdAndView(idAnnonce, null);
        } else {
          getByIdAndView(idAnnonce, u.getId());
        }
      }
      AnnonceEtFavori a = (AnnonceEtFavori) entityManager.createNativeQuery(
          """
                select a.*, f.date_ajout
                from v_annonce_gen_a_jour a
                left outer join annonce_favori f on a.id = f.id_annonce and f.id_utilisateur = :user where a.id = :id
              """,
          AnnonceEtFavori.class).setParameter("user", u.getId()).setParameter("id", idAnnonce).getSingleResult();
      a.setPhotos(findPhotos(a.getId()));
      return new Annonce(a);
    } catch (NoResultException e) {
      e.printStackTrace();
      throw new NotFoundException();
    }
  }

  public void getByIdAndView(int idAnnonce, Integer iduser) {
    VueAnnonce vueAnnonce = new VueAnnonce();
    vueAnnonce.setIdUtilisateur(iduser);
    vueAnnonce.setIdAnnonce(idAnnonce);
    try {
      vueAnnonceService.save(vueAnnonce);
      annonceRepository.addView(idAnnonce);
    } catch (DataIntegrityViolationException e) {
      e.printStackTrace();
      System.out.println("deja vu");
    }
  }

  public HistoriqueAnnonceDTO findHistorique(int idAnnonce)
      throws NotFoundException, ValidationException, AuthException {
    Annonce annonce = find(idAnnonce);

    Utilisateur u = utilisateurService.getAuthenticated();
    if (annonce.getIdUtilisateur() != u.getId() && u.getIdRole() != 1) {
      throw new ValidationException("Accès interdit. Ceci n'est pas votre annonce");
    }
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

  public List<AnnonceDTO> findByUser(int page, int taille) throws AuthException {
    Utilisateur u = utilisateurService.getAuthenticated();
    List<AnnonceGeneral> aG = aGeneralService.findByIdUtilisateur(u.getId(), page, taille);
    List<AnnonceDTO> annonces = new ArrayList<>();
    for (AnnonceGeneral a : aG) {
      AnnonceDTO dto = new AnnonceDTO(a);
      dto.setPhotos(findPhotos(a.getId()));
      annonces.add(dto);
    }
    return annonces;
  }

  @Transactional(rollbackOn = Exception.class)
  public void valider(int idAnnonce)
      throws NotFoundException, ValidationException {
    AnnonceGeneral a = this.aGeneralService.find(idAnnonce);
    checkValidation(a);

    HistoriqueAnnonce historique = new HistoriqueAnnonce();
    historique.setIdAnnonce(idAnnonce);
    historique.setDateMaj(LocalDateTime.now(ApplicationTimeZone.ZONE_ID));
    historique.setStatus(params.getAnnonceValide());

    updateStatus(idAnnonce, params.getAnnonceValide());
    historiqueService.save(historique);

    // sending notification
    List<String> tokens = notifPushService.getTokenOf(a.getIdUtilisateur());
    NotificationPush notif = new NotificationPush("Annonce validée",
        "L'admin a validé votre annonce sur " + a.getNomMarque() + " - " + a.getNomModele() + ". Ref: "
            + a.getReference(),
        tokens);
    try {
      if (tokens.size() > 0) {
        notifPushService.sendNotif(notif);
      }
      System.out.println("notif envoyé");
    } catch (FirebaseMessagingException | InterruptedException | ExecutionException e) {
      e.printStackTrace();
      System.out.println("==========================================");
      System.out.println("Erreur lors de l'envoi de notification après validation de l'annonce " + a.getReference()
          + " " + e.getMessage());
      System.out.println("==============================================");
    }
  }

  @Transactional
  public void refuser(int idAnnonce)
      throws NotFoundException, ValidationException {
    AnnonceGeneral a = aGeneralService.find(idAnnonce);
    checkValidation(a);

    HistoriqueAnnonce historique = new HistoriqueAnnonce();
    historique.setIdAnnonce(idAnnonce);
    historique.setDateMaj(LocalDateTime.now(ApplicationTimeZone.ZONE_ID));
    historique.setStatus(params.getAnnonceRefuse());

    updateStatus(idAnnonce, params.getAnnonceRefuse());
    historiqueService.save(historique);

    // sending notification
    List<String> tokens = notifPushService.getTokenOf(a.getIdUtilisateur());
    NotificationPush notif = new NotificationPush("Annonce validée",
        "L'admin a refusé votre annonce sur " + a.getNomMarque() + " - " + a.getNomModele(), tokens);
    try {
      if (tokens.size() > 0) {
        notifPushService.sendNotif(notif);
        System.out.println("notif envoyé");
      }
    } catch (FirebaseMessagingException | InterruptedException | ExecutionException e) {
      e.printStackTrace();
      System.out.println("==========================================");
      System.out.println("Erreur lors de l'envoi de notification après refus de l'annonce  " + a.getReference() + " "
          + e.getMessage());
      System.out.println("==============================================");
    }
  }

  public void checkValidation(AnnonceGeneral a) throws ValidationException {
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
    historiqueAnnonce.setDateMaj(LocalDateTime.now(ApplicationTimeZone.ZONE_ID));
    historiqueAnnonce.setStatus(params.getAnnonceCree());
    historiqueService.save(historiqueAnnonce);

    return model;
  }

  public List<AnnonceDTO> getAllNonValide(int page, int taille) {
    List<AnnonceGeneral> aGenerals = aGeneralService.findNonValide(page, taille);
    List<AnnonceDTO> a = new ArrayList<>();
    for (AnnonceGeneral annonce : aGenerals) {
      AnnonceDTO dto = new AnnonceDTO(annonce);
      dto.setPhotos(findPhotos(annonce.getId()));
      a.add(dto);
    }
    return a;
  }

  public List<Annonce> getAllNonValide() {
    return annonceRepository.getAllNonValide();
  }

  // SYSOUT QUERY
  public List<AnnonceDTO> findComplex(RechercheAnnonce rechercheAnnonce, int page, int taille) {
    System.out.println("RECHERCHE COMPLEXE");
    int idUtilisateur = 0;

    try {
      Utilisateur u = utilisateurService.getAuthenticated();
      idUtilisateur = u.getId();
    } catch (AuthException e) {
      System.out.println("Recherche sans connexion");
    }

    String query = rechercheAnnonce.generateSql();

    query = query.replace("%id%", String.valueOf(idUtilisateur));
    if (taille != 0) {
      query += " order by a.date_maj desc limit " + taille + " offset(" + page + " - 1)*" + taille;
    }

    System.out.println(query + "==========================");
    List<AnnonceEtFavori> annonces = (List<AnnonceEtFavori>) entityManager
        .createNativeQuery(
            query,
            AnnonceEtFavori.class)
        .getResultList();
    List<AnnonceDTO> a = new ArrayList<>();
    for (AnnonceEtFavori annonce : annonces) {
      AnnonceDTO dto = new AnnonceDTO(annonce);
      dto.setPhotos(findPhotos(annonce.getId()));
      a.add(dto);
    }
    return a;
  }

  @Override
  @Transactional(rollbackOn = Exception.class)
  public Annonce update(Annonce model, int id) throws NotFoundException, ValidationException {
    System.out.println("update annonce");
    Annonce a = findById(id);
    System.out.println(a.getStatus() + " ============= status");
    if (a.getStatus() != 0) {
      throw new ValidationException("Seule les annonces non valides peuvent être modifiées.");
    }
    if (model.getVoiture() != null) {
      model.setVoiture(voitureService.save(model.getVoiture()));
    }
    return super.update(model, id);
  }
}
