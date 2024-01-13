package com.cloud.voiture.models.voiture;

import java.lang.reflect.Parameter;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Modele extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    @NotNull
    String nom;

    @NotNull
    @Digits(fraction = 0, integer = 2, message = "Le nombre de place doit être numérique.")
    @Min(value = 2, message = "Le nombre de place doit être supérieur à 2.")
    int nbPlace;

    @NotNull
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
    Categorie categorie;

    @NotNull(message = "La marque est obligatoire")
    @Column(name = "id_marque")
    @Digits(fraction = 0, integer = 3, message = "La marque doit être l'identifiant d'une marque.")
    @Min(value = 1, message = "La marque est obligatoire.")
    int idMarque;

    @ManyToOne
    @JoinColumn(name = "id_marque", insertable = false, updatable = false)
    Marque marque;

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
