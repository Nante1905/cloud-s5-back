package com.cloud.voiture.models.annonce.annoncePhoto;

import com.cloud.voiture.models.annonce.Annonce;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "annonce_photo")
public class AnnoncePhoto {
    @JsonIgnore
    @EmbeddedId
    AnnoncePhotoID id = new AnnoncePhotoID();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_annonce")
    @MapsId("idAnnonce")
    Annonce annonce;

    // @JsonIgnore
    // @ManyToOne(cascade = CascadeType.PERSIST)
    // @JoinColumn(name = "id_annonce")
    // @MapsId("idAnnonce")
    // AnnonceGeneral annonceGeneral;

    @Transient
    String url;

    public void setIdAnnonce(int idAnnonce) {
        id.setIdAnnonce(idAnnonce);
    }

    // public String getPhotoUrl() {
    // return photoUrl;
    // }

    // public void setPhotoUrl(String photoUrl) {
    // this.photoUrl = photoUrl;
    // }

    public AnnoncePhoto() {
    }

    public AnnoncePhoto(AnnoncePhotoID id) {
        this.id = id;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public AnnoncePhotoID getId() {
        return id;
    }

    public void setId(AnnoncePhotoID id) {
        this.id = id;
    }

    public String getUrl() {
        return id.getPhotoUrl();
    }

    public void setUrl(String url) {
        this.url = url;
        id.setPhotoUrl(url);
    }

}
