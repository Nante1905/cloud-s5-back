package com.cloud.voiture.models.annonce;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.voiture.Voiture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class Annonce extends GenericModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String reference;

  @NotBlank(message = "")
  @NotNull(message = "")
  String description;

  int status;

  @Column(name = "date_creation")
  Date dateCreation;

  @NotNull(message = "")
  @Min(value = 0, message = "Le prix doit être strictement positif.")
  Double prix;

  Double commission;

  @Column(name = "nb_vue")
  Integer nbVues;

  @Column(name = "id_utilisateur")
  @Min(value = 1, message = "L'utilisateur est obligatoire.")
  int idUtilisateur;

  @ManyToOne
  @JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
  Utilisateur utilisateur;

  @OneToOne
  @JoinColumn(name = "id_voiture", referencedColumnName = "id", insertable = false, updatable = false)
  Voiture voiture;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getReference() {
    return reference;
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

  public Date getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
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
}
