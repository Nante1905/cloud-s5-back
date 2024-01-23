package com.cloud.voiture.models.annonce.favori;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "annonce_favori")
public class Favori {

  @JsonIgnore
  @EmbeddedId
  FavoriAnnonceID id = new FavoriAnnonceID();

  LocalDateTime dateAjout = LocalDateTime.now();

  public int getIdAnnonce() {
    return getId().getIdAnnonce();
  }

  public int getIdUtilisateur() {
    return getId().getIdUtilisateur();
  }

  public FavoriAnnonceID getId() {
    return id;
  }

  public void setId(FavoriAnnonceID id) {
    this.id = id;
  }

  public LocalDateTime getDateAjout() {
    return dateAjout;
  }

  public void setDateAjout(LocalDateTime dateAjout) {
    this.dateAjout = dateAjout;
  }

}
