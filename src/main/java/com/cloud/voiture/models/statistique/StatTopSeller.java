package com.cloud.voiture.models.statistique;

import java.math.BigDecimal;

import jakarta.persistence.Column;

public class StatTopSeller {
  @Column(name = "id")
  int idUtilisateur;
  @Column(name = "nom")
  String nom;
  @Column(name = "prenom")
  String prenom;

  double valide;
  double vendu;
  double commission;
  double pourcentage;

  public StatTopSeller(
      Integer id,
      String nom,
      String prenom,
      Long valide,
      Long vendu,
      BigDecimal commission,
      BigDecimal pourcentage) {
    setIdUtilisateur(id);
    setNom(nom);
    setPrenom(prenom);
    setCommission(commission);
    setValide(valide);
    setVendu(vendu);
    setPourcentage(pourcentage);
  }

  public void setValide(Long valide) {
    setValide(Double.valueOf(valide));
  }

  public void setVendu(Long vendu) {
    setVendu(Double.valueOf(vendu));
  }

  public void setPourcentage(BigDecimal pourcentage) {
    setPourcentage(Double.valueOf(String.valueOf(pourcentage)));
  }

  public void setCommission(BigDecimal c) {
    setCommission(Double.valueOf(String.valueOf(c)));
  }

  public double getValide() {
    return valide;
  }

  public void setValide(double valide) {
    this.valide = valide;
  }

  public double getVendu() {
    return vendu;
  }

  public void setVendu(double vendu) {
    this.vendu = vendu;
  }

  public double getCommission() {
    return commission;
  }

  public void setCommission(double commission) {
    this.commission = commission;
  }

  public double getPourcentage() {
    return pourcentage;
  }

  public void setPourcentage(double pourcentage) {
    this.pourcentage = pourcentage;
  }

  public int getIdUtilisateur() {
    return idUtilisateur;
  }

  public void setIdUtilisateur(int idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nomUtilisateur) {
    this.nom = nomUtilisateur;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
}
