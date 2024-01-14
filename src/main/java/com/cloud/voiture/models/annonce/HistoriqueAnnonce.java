package com.cloud.voiture.models.annonce;

import java.time.LocalDateTime;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cloud.voiture.crud.model.GenericModel;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "historique_annonce")
public class HistoriqueAnnonce extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "date_maj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime dateMaj;
    @Column(name = "id_annonce")
    int idAnnonce;
    int status;

    @ManyToOne
    @JoinColumn(name = "id_annonce", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Annonce annonce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(LocalDateTime dateMaj) {
        this.dateMaj = dateMaj;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
