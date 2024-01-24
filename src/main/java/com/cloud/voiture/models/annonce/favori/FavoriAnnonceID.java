package com.cloud.voiture.models.annonce.favori;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * FavoriAnnonceID
 */
@Embeddable
public class FavoriAnnonceID implements Serializable {
    @Column(name = "id_utilisateur")
    int idUtilisateur;

    @Column(name = "id_annonce")
    int idAnnonce;

    public FavoriAnnonceID() {
    }

    public FavoriAnnonceID(int idUtilisateur, int idAnnonce) {
        setIdUtilisateur(idUtilisateur);
        setIdAnnonce(idAnnonce);
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

}