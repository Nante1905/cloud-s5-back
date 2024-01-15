package com.cloud.voiture.models.statistique;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import java.math.BigDecimal;

public class StatTopSeller {
  @Column(name = "id")
  int idUtilisateur;
  @Column(name = "nom")
  String nomUtilisateur;

  double valide;
  double vendu;
  double commission;
  double pourcentage;

  public StatTopSeller(
    Integer id,
    String nom,
    Long valide,
    Long vendu,
    BigDecimal commission,
    BigDecimal pourcentage
  ) {
    setIdUtilisateur(id);
    setNomUtilisateur(nom);
    setCommission(commission);
    setValide(valide);
    setVendu(vendu);
    setPourcentage(pourcentage);
  }
  public void setValide(Long valide){
    setValide(Double.valueOf(valide));
  }
  public void setVendu(Long vendu){
    setVendu(Double.valueOf(vendu));
  }
  public void setPourcentage(BigDecimal pourcentage){
    setVendu(Double.valueOf(String.valueOf(pourcentage)));
  }
  public void setCommission(BigDecimal c){
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

  public String getNomUtilisateur() {
    return nomUtilisateur;
  }

  public void setNomUtilisateur(String nomUtilisateur) {
    this.nomUtilisateur = nomUtilisateur;
  }
}
