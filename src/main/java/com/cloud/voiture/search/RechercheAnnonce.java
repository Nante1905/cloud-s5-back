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
      sql = sql + " where ";
    }
    else{
        System.out.println(sql);
        return sql;
    }
    boolean firstCondition = true;

    if ( motCle != null && ! motCle.isEmpty()) {
      sql = appendCondition(
        sql,
        "lower(description) LIKE lower('%" +  motCle + "%')",
        firstCondition
      );
      firstCondition = false;
    }

    if ( categorie != null) {
      sql = appendCondition(
        sql,
        "id_categorie = " +  categorie.getId() ,
        firstCondition
      );
      firstCondition = false;
    }

    if ( marque != null) {
      sql = appendCondition(
        sql,
        "id_marque = " +  marque.getId(),
        firstCondition
      );
      firstCondition = false;
    }

    if ( modele != null) {
      sql = appendCondition(
        sql,
        "id_modele = " +  modele.getId(),
        firstCondition
      );
      firstCondition = false;
    }

    if ( anneeMiseCirculation != null) {
      sql = appendCondition(
        sql,
        "annee_sortie = "+anneeMiseCirculation,
        firstCondition
      );
      firstCondition = false;
    }

    if ( prixMin != null) {
      sql = appendCondition(sql, "prix >= " +  prixMin, firstCondition);
      firstCondition = false;
    }

    if ( prixMax != null) {
      sql = appendCondition(sql, "prix <= " +  prixMax, firstCondition);
      firstCondition = false;
    }
    System.out.println(sql);
    return sql;
  }

  public String appendCondition(
    String sql,
    String condition,
    boolean firstCondition
  ) {
    if (!firstCondition) {
      sql += " AND ";
    }
    sql += condition;
    return sql;
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
