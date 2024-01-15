package com.cloud.voiture.models.annonce;

import java.util.List;

import com.cloud.voiture.models.voiture.Modele;

public class HistoriqueAnnonceDTO {
    int id;
    String marque;
    String modele;
    List<HistoriqueAnnonceMin> historiques;

    public HistoriqueAnnonceDTO() {
    }

    public HistoriqueAnnonceDTO(Annonce annonce, List<HistoriqueAnnonceMin> historiques) {
        setId(annonce.getId());
        Modele m = annonce.getVoiture().getModele();
        setMarque(m.getMarque().getNom());
        setModele(m.getNom());
        setHistoriques(historiques);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public List<HistoriqueAnnonceMin> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoriqueAnnonceMin> historiques) {
        this.historiques = historiques;
    }

}
