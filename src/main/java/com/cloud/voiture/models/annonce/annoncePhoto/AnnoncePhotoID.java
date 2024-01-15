package com.cloud.voiture.models.annonce.annoncePhoto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AnnoncePhotoID implements Serializable {
    @Column(name = "id_annonce", insertable = false, updatable = false)
    int idAnnonce;

    @Column(name = "photo_url")
    String photoUrl;

    public AnnoncePhotoID(int idAnnonce, String photoUrl) {
        setIdAnnonce(idAnnonce);
        setPhotoUrl(photoUrl);
    }

    public AnnoncePhotoID() {
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
