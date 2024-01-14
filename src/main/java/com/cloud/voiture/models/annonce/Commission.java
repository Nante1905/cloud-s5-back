package com.cloud.voiture.models.annonce;

import com.cloud.voiture.crud.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "commission")
public class Commission extends GenericModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  double pourcentage;

  @Column(name = "date_debut")
  LocalDateTime dateAjout = LocalDateTime.now();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getPourcentage() {
    return pourcentage;
  }

  public void setPourcentage(double pourcentage) {
    this.pourcentage = pourcentage;
  }

  public LocalDateTime getDateAjout() {
    return dateAjout;
  }

  public void setDateAjout(LocalDateTime dateAjout) {
    this.dateAjout = dateAjout;
  }
}
