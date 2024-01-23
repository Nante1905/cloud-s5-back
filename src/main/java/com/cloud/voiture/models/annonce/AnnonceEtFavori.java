package com.cloud.voiture.models.annonce;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * AnnonceEtFavori
 */
@MappedSuperclass
public class AnnonceEtFavori extends AnnonceValide {

  @Column(name = "date_ajout")
  LocalDateTime dateAjout;

  public AnnonceEtFavori(Integer id, String reference, String description, Integer status, Timestamp dateCreation,
      BigDecimal prix, BigDecimal commission, Integer nbVues, Integer idUtilisateur, Integer idVoiture,
      BigDecimal consommation, Integer kilometrage, Integer etat, Integer idCouleur, Integer idModele,
      Integer idBoiteVitesse, Integer idEnergie, String nomCouleur, String hexa, String nomModele, Integer nbPlace,
      Integer nbPorte, Integer anneeSortie, Integer idCategorie, Integer idMarque, String nomVitesse, String nomEnergie,
      String nomCategorie, String nomMarque, String logo, String utilisateurNom, String utilisateurPrenom,
      Timestamp dateInscription, String adresse, Timestamp validation, Timestamp dateAjout) {
    super(id, reference, description, status, dateCreation, prix, commission, nbVues, idUtilisateur,
        idVoiture,
        consommation, kilometrage, etat, idCouleur, idModele, idBoiteVitesse, idEnergie, nomCouleur, hexa, nomModele,
        nbPlace, nbPorte, anneeSortie, idCategorie, idMarque, nomVitesse, nomEnergie, nomCategorie, nomMarque, logo,
        utilisateurNom, utilisateurPrenom, dateInscription, adresse, validation);

    setDateAjout(dateAjout);
    // TODO Auto-generated constructor stub
  }

  public boolean isFavori() {
    return dateAjout != null;
  }

  public LocalDateTime getDateAjout() {
    return dateAjout;
  }

  public void setDateAjout(LocalDateTime dateAjout) {
    this.dateAjout = dateAjout;
  }

  public void setDateAjout(Timestamp d) {
    if (d == null) {
      dateAjout = null;
    } else {
      setDateAjout(d.toLocalDateTime());

    }
  }

}