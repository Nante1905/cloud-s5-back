package com.cloud.voiture.models.annonce;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_annonce_gen_valide")
@MappedSuperclass
public class AnnonceValide extends AnnonceGeneral {
    @Column(name = "date_maj")
    LocalDateTime validation;

    public AnnonceValide(Integer id, String reference, String description, Integer status, Timestamp dateCreation,
            BigDecimal prix, BigDecimal commission, Integer nbVues, Integer idUtilisateur, Integer idVoiture,
            BigDecimal consommation, Integer kilometrage, Integer etat, Integer idCouleur, Integer idModele,
            Integer idBoiteVitesse, Integer idEnergie, String nomCouleur, String hexa, String nomModele,
            Integer nbPlace, Integer nbPorte, Integer anneeSortie, Integer idCategorie, Integer idMarque,
            String nomVitesse, String nomEnergie, String nomCategorie, String nomMarque, String logo,
            String utilisateurNom, String utilisateurPrenom, Timestamp dateInscription, String adresse,
            Timestamp validation) {
        super(id, reference, description, status, dateCreation, prix, commission, nbVues, idUtilisateur, idVoiture,
                consommation, kilometrage, etat, idCouleur, idModele, idBoiteVitesse, idEnergie, nomCouleur, hexa,
                nomModele, nbPlace, nbPorte, anneeSortie, idCategorie, idMarque, nomVitesse, nomEnergie, nomCategorie,
                nomMarque, logo, utilisateurNom, utilisateurPrenom, dateInscription, adresse);
        setValidation(validation);
    }

    private void setValidation(Timestamp v) {
        if (v == null) {
            this.validation = null;
        } else {
            setValidation(v.toLocalDateTime());
        }
    }

    @Override
    public LocalDateTime getDateCreation() {
        return validation;
    }

    public LocalDateTime getValidation() {
        return validation;
    }

    public void setValidation(LocalDateTime validation) {
        this.validation = validation;
    }

}
