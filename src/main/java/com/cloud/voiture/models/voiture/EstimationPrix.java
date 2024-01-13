package com.cloud.voiture.models.voiture;

public class EstimationPrix {

  Voiture voiture;

  public Voiture getVoiture() {
    return voiture;
  }

  public void setVoiture(Voiture voiture) {
    this.voiture = voiture;
  }

  double prix;

  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }
}
