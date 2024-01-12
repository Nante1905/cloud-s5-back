package com.cloud.voiture.models.annonce;

import com.cloud.voiture.crud.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "commission")
public class Commission extends GenericModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  double pourcentage;

  @Column(name = "date_debut")
  LocalDate dateAjout = LocalDate.now();

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

  public LocalDate getDateAjout() {
    return dateAjout;
  }

  public void setDateAjout(LocalDate dateAjout) {
    this.dateAjout = dateAjout;
  }
}
