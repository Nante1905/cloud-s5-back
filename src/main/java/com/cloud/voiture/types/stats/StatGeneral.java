package com.cloud.voiture.types.stats;

public class StatGeneral {
    double montant;
    double creationVenteDelai;
    double nbVente;
    double nbAnnonce;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getCreationVenteDelai() {
        return creationVenteDelai;
    }

    public void setCreationVenteDelai(double creationVenteDelai) {
        this.creationVenteDelai = creationVenteDelai;
    }

    public double getNbVente() {
        return nbVente;
    }

    public void setNbVente(double nbVente) {
        this.nbVente = nbVente;
    }

    public double getNbAnnonce() {
        return nbAnnonce;
    }

    public void setNbAnnonce(double nbAnnonce) {
        this.nbAnnonce = nbAnnonce;
    }

}
