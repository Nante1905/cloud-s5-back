package com.cloud.voiture.models.annonce.DTO;

public class ModeleDTO {
    int id;
    String nom;

    public ModeleDTO() {
    }

    public ModeleDTO(int id, String nom) {
        setId(id);
        setNom(nom);
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

}
