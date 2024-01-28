package com.cloud.voiture.models.voiture;

import java.time.LocalDate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.exceptions.ValidationException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Modele extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "")
    @NotNull(message = "")
    String nom;

    @NotNull(message = "")
    @Digits(fraction = 0, integer = 2, message = "Le nombre de place doit être numérique.")
    @Min(value = 2, message = "Le nombre de place doit être supérieur à 2.")
    int nbPlace;

    @NotNull(message = "")
    @Digits(fraction = 0, integer = 2, message = "Le nombre de porte doit être numérique.")
    @Min(value = 2, message = "Le nombre de porte doit être supérieur à 2.")
    int nbPorte;

    @Digits(fraction = 0, integer = 4, message = "L'année de sortie n'est pas valide")
    int anneeSortie;

    @Column(name = "id_categorie")
    @Digits(fraction = 0, integer = 3, message = "La catégorie doit être l'identifiant d'une catégorie.")
    @Min(value = 1, message = "La catégorie est obligatoire.")
    int idCategorie;

    @ManyToOne
    @JoinColumn(name = "id_categorie", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Categorie categorie;

    @NotNull(message = "La marque est obligatoire")
    @Column(name = "id_marque")
    @Digits(fraction = 0, integer = 3, message = "La marque doit être l'identifiant d'une marque.")
    @Min(value = 1, message = "La marque est obligatoire.")
    int idMarque;

    @ManyToOne
    @JoinColumn(name = "id_marque", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Marque marque;

    public Modele(int id, String nom) {
        setId(id);
        setNom(nom);
    }

    public Modele(int id, @NotBlank(message = "") @NotNull(message = "") String nom,
            @NotNull(message = "") @Digits(fraction = 0, integer = 2, message = "Le nombre de place doit être numérique.") @Min(value = 2, message = "Le nombre de place doit être supérieur à 2.") int nbPlace,
            @NotNull(message = "") @Digits(fraction = 0, integer = 2, message = "Le nombre de porte doit être numérique.") @Min(value = 2, message = "Le nombre de porte doit être supérieur à 2.") int nbPorte,
            @Digits(fraction = 0, integer = 4, message = "L'année de sortie n'est pas valide") int anneeSortie,
            Categorie categorie, Marque marque) {
        this.id = id;
        this.nom = nom;
        this.nbPlace = nbPlace;
        this.nbPorte = nbPorte;
        this.anneeSortie = anneeSortie;
        this.categorie = categorie;
        this.marque = marque;
    }

    public Modele() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public int getNbPorte() {
        return nbPorte;
    }

    public void setNbPorte(int nbPorte) {
        this.nbPorte = nbPorte;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie)
            throws ValidationException {
        if (anneeSortie > LocalDate.now().getYear()) {
            throw new ValidationException(
                    "L'année de sortie du modèle doit être inférieure à " +
                            LocalDate.now().getYear());

            // throw new MethodArgumentNotValidException(
            // new MethodParameter(getClass().getDeclaredMethod("setAnneeSortie",
            // int.class), 0), );

        }
        this.anneeSortie = anneeSortie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

}
