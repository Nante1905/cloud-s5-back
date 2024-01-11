package com.cloud.voiture.models.statistique;

import java.math.BigDecimal;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cloud.voiture.models.voiture.Marque;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

public class MarqueBenefice {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id_marque")
    int idMarque;
    double montant;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "nom_marque")
    String nomMarque;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String logo;

    @Transient
    Marque marque;

    public MarqueBenefice() {
    }

    public MarqueBenefice(int idMarque, String nomMarque, String logo, BigDecimal montant) {
        setIdMarque(idMarque);
        setMarque(new Marque(idMarque, nomMarque, logo));
        setMontant(montant);
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setMontant(BigDecimal montant) {
        setMontant(montant.doubleValue());
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

}
