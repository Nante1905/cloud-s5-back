package com.cloud.voiture.models.auth;

import java.time.LocalDateTime;

public class UtilisateurDTO {
    int id;
    String nom;
    String prenom;
    LocalDateTime inscription;
    String adresse;

    public UtilisateurDTO(int id, String nom, String prenom, String adresse, LocalDateTime inscription) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setAdresse(adresse);
        setInscription(inscription);
    }

    public UtilisateurDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDateTime getInscription() {
        return inscription;
    }

    public void setInscription(LocalDateTime inscription) {
        this.inscription = inscription;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
