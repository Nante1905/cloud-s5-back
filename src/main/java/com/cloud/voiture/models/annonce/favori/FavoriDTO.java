package com.cloud.voiture.models.annonce.favori;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class FavoriDTO {
    @Column(name = "id_annonce")
    int idAnnonce;
    @Column(name = "id_utilisateur")
    int idUtilisateur;
    @Column(name = "date_maj")
    LocalDateTime validation;
    @Column(name = "date_ajout")
    LocalDateTime dateLike;
    int status;

    public FavoriDTO(Integer idAnnonce, Integer idUtilisateur, Timestamp validation, Timestamp dateLike,
            Integer status) {
        setIdAnnonce(idAnnonce);
        setIdUtilisateur(idUtilisateur);
        setValidation(validation);
        setDateLike(dateLike);
        setStatus(status);
    }

    private void setValidation(Timestamp v) {
        if (v == null) {
            this.validation = null;
        } else {
            setValidation(v.toLocalDateTime());
        }
    }

    private void setDateLike(Timestamp v) {
        if (v == null) {
            this.dateLike = null;
        } else {
            setDateLike(v.toLocalDateTime());
        }
    }

    public FavoriDTO() {
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public LocalDateTime getValidation() {
        return validation;
    }

    public void setValidation(LocalDateTime validation) {
        this.validation = validation;
    }

    public LocalDateTime getDateLike() {
        return dateLike;
    }

    public void setDateLike(LocalDateTime dateLike) {
        this.dateLike = dateLike;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
