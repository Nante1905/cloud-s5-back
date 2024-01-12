package com.cloud.voiture.models.annonce;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.models.auth.Utilisateur;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "annonce_favori")
public class Favori extends GenericModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @OneToOne
  @JoinColumn(
    name = "id_utilisateur",
    referencedColumnName = "id",
    insertable = false,
    updatable = false
  )
  Utilisateur utilisateur;

  @OneToOne
  @JoinColumn(
    name = "id_annonce",
    referencedColumnName = "id",
    insertable = false,
    updatable = false
  )
  Annonce annonce;

  LocalDate dateAjout = LocalDate.now();

  public Utilisateur getUtilisateur() {
    return utilisateur;
  }

  public void setUtilisateur(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;
  }

  public Annonce getAnnonce() {
    return annonce;
  }

  public void setAnnonce(Annonce annonce) {
    this.annonce = annonce;
  }

  public LocalDate getDateAjout() {
    return dateAjout;
  }

  public void setDateAjout(LocalDate dateAjout) {
    this.dateAjout = dateAjout;
  }

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}
}
