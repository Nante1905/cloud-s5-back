package com.cloud.voiture.models.statistique;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class StatInscription {

  int mois;

  @Column(name = "nb_inscription")
  int nbInscrit;

  public StatInscription() {}

  public int getMois() {
    return mois;
  }

  public StatInscription(Long nb, Integer mois) {
    setMois(mois);
    setNbInscrit(nb);
  }

  public void setNbInscrit(Long inscrit) {
    setNbInscrit(Integer.valueOf(String.valueOf(inscrit)));
  }

  public void setMois(int mois) {
    this.mois = mois;
  }

  public int getNbInscrit() {
    return nbInscrit;
  }

  public void setNbInscrit(int nbInscrit) {
    this.nbInscrit = nbInscrit;
  }
}
