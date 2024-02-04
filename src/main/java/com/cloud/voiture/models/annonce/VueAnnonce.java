package com.cloud.voiture.models.annonce;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.models.auth.Utilisateur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "vue_annonce")
@Entity
public class VueAnnonce extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "id_utilisateur")
    int idUtilisateur;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
    Utilisateur utilisateur;
    @Column(name = "id_annonce")
    int idAnnonce;
    @ManyToOne
    @JoinColumn(name = "id_annonce", insertable = false, updatable = false)
    Annonce annonce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
}
