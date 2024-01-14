package com.cloud.voiture.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constant {
    @Value("${app.defaultPageSize}")
    private int defaultPageSize;
    @Value("${app.annonceStatus.cree}")
    private int annonceCree;
    @Value("${app.annonceStatus.valide}")
    private int annonceValide;
    @Value("${app.annonceStatus.refuse}")
    private int annonceRefuse;
    @Value("${app.annonceStatus.vendu}")
    private int annonceVendu;
    @Value("${app.estimation.etat.marge}")
    private int margeEtat;
   @Value("${app.annonce.reference.prefix}")
  private String annonceRefPrefix;
    

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public int getAnnonceCree() {
        return annonceCree;
    }

    public void setAnnonceCree(int annonceCree) {
        this.annonceCree = annonceCree;
    }

    public int getAnnonceValide() {
        return annonceValide;
    }

    public void setAnnonceValide(int annonceValide) {
        this.annonceValide = annonceValide;
    }

    public int getAnnonceRefuse() {
        return annonceRefuse;
    }

    public void setAnnonceRefuse(int annonceRefuse) {
        this.annonceRefuse = annonceRefuse;
    }

    public int getAnnonceVendu() {
        return annonceVendu;
    }

    public void setAnnonceVendu(int annonceVendu) {
        this.annonceVendu = annonceVendu;
    }

    public int getMargeEtat() {
        return margeEtat;
    }

    public void setMargeEtat(int margeEtat) {
        this.margeEtat = margeEtat;
    }


public String getAnnonceRefPrefix() {
    return annonceRefPrefix;
}

public void setAnnonceRefPrefix(String annonceRefPrefix) {
    this.annonceRefPrefix = annonceRefPrefix;
}
}
