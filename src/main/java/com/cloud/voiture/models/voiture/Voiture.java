package com.cloud.voiture.models.voiture;

import com.cloud.voiture.crud.model.GenericModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Voiture extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    float consommation;
    int kilometrage;
    int etat;

    @Column(name = "id_couleur")
    int idCouleur;
    @ManyToOne
    @JoinColumn(name = "id_couleur", insertable = false, updatable = false)
    Couleur couleur;

    @Column(name = "id_modele")
    int idModele;
    @ManyToOne
    @JoinColumn(name = "id_modele", insertable = false, updatable = false)
    Modele modele;

    @Column(name = "id_boite_vitesse")
    int idBoiteVitesse;
    @ManyToOne
    @JoinColumn(name = "id_boite_vitesse", insertable = false, updatable = false)
    Vitesse vitesse;

    @Column(name = "id_energie")
    int idEnergie;
    @ManyToOne
    @JoinColumn(name = "id_energie", insertable = false, updatable = false)
    Energie energie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getConsommation() {
        return consommation;
    }

    public void setConsommation(float consommation) {
        this.consommation = consommation;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(int idCouleur) {
        this.idCouleur = idCouleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public int getIdBoiteVitesse() {
        return idBoiteVitesse;
    }

    public void setIdBoiteVitesse(int idBoiteVitesse) {
        this.idBoiteVitesse = idBoiteVitesse;
    }

    public Vitesse getVitesse() {
        return vitesse;
    }

    public void setVitesse(Vitesse vitesse) {
        this.vitesse = vitesse;
    }

    public int getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(int idEnergie) {
        this.idEnergie = idEnergie;
    }

    public Energie getEnergie() {
        return energie;
    }

    public void setEnergie(Energie energie) {
        this.energie = energie;
    }

}
