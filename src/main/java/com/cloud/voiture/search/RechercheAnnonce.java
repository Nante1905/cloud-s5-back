package com.cloud.voiture.search;

import com.cloud.voiture.models.voiture.Categorie;
import com.cloud.voiture.models.voiture.Marque;
import com.cloud.voiture.models.voiture.Modele;

public class RechercheAnnonce {

  String motCle;
  Categorie categorie;
  Marque marque;
  Modele modele;
  Double anneeMiseCirculation;
  Double prixMin;
  Double prixMax;

  public String generateSql() {
    String sql = "select id_annonce from v_annonce ";
    if (
      motCle != null ||
      categorie != null ||
      marque != null ||
      modele != null ||
      anneeMiseCirculation != null ||
      prixMax != null ||
      prixMin != null ||
      prixMax != null
    ) {
      sql = sql + " where";
    }
    else{
        System.out.println(sql);
        return sql;
    }
    boolean firstCondition = true;

    if ( motCle != null && ! motCle.isEmpty()) {
      appendCondition(
        sql,
        "motCle LIKE '%" +  motCle + "%'",
        firstCondition
      );
      firstCondition = false;
    }

    if ( categorie != null) {
      appendCondition(
        sql,
        "categorie = " +  categorie.getId() ,
        firstCondition
      );
      firstCondition = false;
    }

    if ( marque != null) {
      appendCondition(
        sql,
        "marque = " +  marque.getId(),
        firstCondition
      );
      firstCondition = false;
    }

    if ( modele != null) {
      appendCondition(
        sql,
        "modele = " +  modele.getId(),
        firstCondition
      );
      firstCondition = false;
    }

    if ( anneeMiseCirculation != null) {
      appendCondition(
        sql,
        "anneeMiseCirculation = "+anneeMiseCirculation,
        firstCondition
      );
      firstCondition = false;
    }

    if ( prixMin != null) {
      appendCondition(sql, "prix >= " +  prixMin, firstCondition);
      firstCondition = false;
    }

    if ( prixMax != null) {
      appendCondition(sql, "prix <= " +  prixMax, firstCondition);
      firstCondition = false;
    }
    System.out.println(sql);
    return sql;
  }

  public void appendCondition(
    String sql,
    String condition,
    boolean firstCondition
  ) {
    if (!firstCondition) {
      sql += " AND ";
    }
    sql += condition;
  }

  public String getMotCle() {
    return motCle;
  }

  public void setMotCle(String motCle) {
    this.motCle = motCle;
  }

  public Categorie getCategorie() {
    return categorie;
  }

  public void setCategorie(Categorie categorie) {
    this.categorie = categorie;
  }

  public Marque getMarque() {
    return marque;
  }

  public void setMarque(Marque marque) {
    this.marque = marque;
  }

  public Modele getModele() {
    return modele;
  }

  public void setModele(Modele modele) {
    this.modele = modele;
  }

  public double getAnneeMiseCirculation() {
    return anneeMiseCirculation;
  }

  public void setAnneeMiseCirculation(double anneeMiseCirculation) {
    this.anneeMiseCirculation = anneeMiseCirculation;
  }

  public double getPrixMin() {
    return prixMin;
  }

  public void setPrixMin(double prixMin) {
    this.prixMin = prixMin;
  }

  public double getPrixMax() {
    return prixMax;
  }

  public void setPrixMax(double prixMax) {
    this.prixMax = prixMax;
  }
}
