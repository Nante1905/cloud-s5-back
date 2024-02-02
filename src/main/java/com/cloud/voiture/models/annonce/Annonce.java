package com.cloud.voiture.models.annonce;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cloud.voiture.config.ApplicationTimeZone;
import com.cloud.voiture.config.Constant;
import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.models.annonce.annoncePhoto.AnnoncePhoto;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.voiture.Categorie;
import com.cloud.voiture.models.voiture.Couleur;
import com.cloud.voiture.models.voiture.Energie;
import com.cloud.voiture.models.voiture.Marque;
import com.cloud.voiture.models.voiture.Modele;
import com.cloud.voiture.models.voiture.Vitesse;
import com.cloud.voiture.models.voiture.Voiture;
import com.cloud.voiture.types.media.Media;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Annonce extends GenericModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String reference;

  @NotBlank(message = "")
  @NotNull(message = "")
  String description;
  int status = 0;

  @Column(name = "date_creation")
  LocalDateTime dateCreation = LocalDateTime.now(ApplicationTimeZone.ZONE_ID);

  @NotNull(message = "")
  @Min(value = 0, message = "Le prix doit être strictement positif.")
  Double prix;

  Double commission;

  @Column(name = "nb_vue")
  Integer nbVues = 0;

  @Column(name = "id_utilisateur")
  int idUtilisateur;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  Utilisateur utilisateur;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_voiture", referencedColumnName = "id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  Voiture voiture;

  @Column(name = "id_voiture")
  int idVoiture;

  @OneToMany(mappedBy = "annonce", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  List<AnnoncePhoto> photos;

  @Transient
  boolean isFavori;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Transient
  Media[] medias;

  public Annonce() {
  }

  public Annonce(AnnonceEtFavori a) {
    setId(a.getId());
    setReference(a.getReference());
    setDescription(a.getDescription());
    setPrix(a.getPrix());
    setStatus(a.getStatus());
    System.out.println("status " + a.getStatus() + " maj  " + a.getValidation());
    if (a.status == 5) {
      setDateCreation(a.getValidation());
    } else {
      setDateCreation(a.getDateCreation());
    }
    setCommission(a.getCommission());
    setUtilisateur(new Utilisateur(a.getIdUtilisateur(), a.getUtilisateurNom(), a.getUtilisateurPrenom(),
        a.getDateInscription(), a.getAdresse()));

    Voiture v = new Voiture(a.getIdVoiture(), a.getConsommation(), a.getKilometrage(), a.getEtat(),
        new Couleur(a.getIdCouleur(), a.getNomCouleur(), a.getHexa()),
        new Modele(a.getIdModele(), a.getNomModele(), a.getNbPlace(), a.getNbPorte(), a.getAnneeSortie(),
            new Categorie(a.getIdCategorie(), a.getNomCategorie()),
            new Marque(a.getIdMarque(), a.getNomMarque(), a.getLogo())),
        new Vitesse(a.getIdBoiteVitesse(), a.getNomVitesse()), new Energie(a.getIdEnergie(), a.getNomEnergie()));
    setVoiture(v);
    setPhotos(a.getPhotos());
    System.out.println("annonce favori " + a.isFavori());
    setFavori(a.isFavori());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getReference() {
    return reference;
  }

  public void defineCommission(Commission commission) {
    setCommission(prix * commission.getPourcentage() / 100);
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Double getPrix() {
    return prix;
  }

  public void setPrix(Double prix) {
    this.prix = prix;
  }

  public Double getCommission() {
    return commission;
  }

  public void setCommission(Double commission) {
    this.commission = commission;
  }

  public Integer getNbVues() {
    return nbVues;
  }

  public void setNbVues(Integer nbVues) {
    this.nbVues = nbVues;
  }

  public int getIdUtilisateur() {
    return idUtilisateur;
  }

  public void setIdUtilisateur(int idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public Utilisateur getUtilisateur() {
    return utilisateur;
  }

  public void setUtilisateur(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;
  }

  public Voiture getVoiture() {
    return voiture;
  }

  public void setVoiture(Voiture voiture) {
    this.voiture = voiture;
  }

  public LocalDateTime getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(LocalDateTime dateCreation) {
    this.dateCreation = dateCreation;
  }

  public void generateReference(int todaysAnnonce, Constant params) {
    todaysAnnonce++;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatted = dateCreation.format(formatter);
    String ref = params.getAnnonceRefPrefix() + "" + formatted + "/" + todaysAnnonce;
    setReference(ref);
  }

  public int getIdVoiture() {
    return idVoiture;
  }

  public void setIdVoiture(int idVoiture) {
    this.idVoiture = idVoiture;
  }

  public List<AnnoncePhoto> getPhotos() {
    return photos;
  }

  public void setPhotos(List<AnnoncePhoto> photos) {
    this.photos = photos;
  }

  public boolean isFavori() {
    return isFavori;
  }

  public void setFavori(boolean isFavori) {
    this.isFavori = isFavori;
  }

  public Media[] getMedias() {
    return medias;
  }

  public void setMedias(Media[] medias) {
    this.medias = medias;
  }
}
