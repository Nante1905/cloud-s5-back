package com.cloud.voiture.search;

import java.util.List;
import java.util.stream.Collectors;

import com.cloud.voiture.models.voiture.Categorie;
import com.cloud.voiture.models.voiture.Marque;
import com.cloud.voiture.models.voiture.Modele;

public class RechercheAnnonce {

  String motCle;
  List<Categorie> categorie;
  List<Marque> marque;
  List<Modele> modele;
  Double anneeMiseCirculation;
  Double prixMin;
  Double prixMax;

  public String generateSql() {
    // String sql = "select id_annonce from v_annonce ";

    String sql = "select a.*, f.date_ajout from v_annonce_gen_valide a left outer join annonce_favori f on a.id = f.id_annonce and f.id_utilisateur = %id% ";

    if (motCle != null ||
        categorie != null ||
        marque != null ||
        modele != null ||
        anneeMiseCirculation != null ||
        prixMax != null ||
        prixMin != null) {
      System.out.println("misy filtre motClé" + motCle + "min " + prixMin);
      sql = sql + " where ";
    } else {
      System.out.println("tsisy filtre " + sql);
      return sql;
    }
    boolean firstCondition = true;

    if (motCle != null && !motCle.isEmpty()) {
      sql = appendCondition(
          sql,
          "lower(description) LIKE lower('%" + motCle + "%')",
          firstCondition);
      firstCondition = false;
    }

    if (categorie != null) {
      sql = appendCondition(
          sql,
          "id_categorie in " + categorie.stream()
              .map(Categorie::getId)
              .collect(Collectors.toList()).toString().replace("[", "(").replace("]", ")"),
          firstCondition);
      firstCondition = false;
    }

    if (marque != null) {
      sql = appendCondition(
          sql,
          "id_marque in " + marque.stream()
              .map(Marque::getId)
              .collect(Collectors.toList()).toString().replace("[", "(").replace("]", ")"),
          firstCondition);
      firstCondition = false;
    }

    if (modele != null) {
      sql = appendCondition(
          sql,
          "id_modele in " + modele.stream()
              .map(Modele::getId)
              .collect(Collectors.toList()).toString().replace("[", "(").replace("]", ")"),
          firstCondition);
      firstCondition = false;
    }

    if (anneeMiseCirculation != null) {
      sql = appendCondition(
          sql,
          "annee_sortie = " + anneeMiseCirculation,
          firstCondition);
      firstCondition = false;
    }

    if (prixMin != null) {
      sql = appendCondition(sql, "prix >= " + prixMin, firstCondition);
      firstCondition = false;
    }

    if (prixMax != null) {
      sql = appendCondition(sql, "prix <= " + prixMax, firstCondition);
      firstCondition = false;
    }
    System.out.println(sql);
    return sql;
  }

  public String appendCondition(
      String sql,
      String condition,
      boolean firstCondition) {
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

  public List<Categorie> getCategorie() {
    return categorie;
  }

  public void setCategorie(List<Categorie> categorie) {
    this.categorie = categorie;
  }

  public List<Marque> getMarque() {
    return marque;
  }

  public void setMarque(List<Marque> marque) {
    this.marque = marque;
  }

  public List<Modele> getModele() {
    return modele;
  }

  public void setModele(List<Modele> modele) {
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
