package com.cloud.voiture.models.annonce;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.models.annonce.annoncePhoto.AnnoncePhoto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_annonce_general")
public class AnnonceGeneral extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String reference;
    String description;
    int status;
    @Column(name = "date_creation")
    LocalDateTime dateCreation;
    double prix;
    double commission;
    @Column(name = "nb_vue")
    int nbVues;
    @Column(name = "id_utilisateur")
    int idUtilisateur;
    @Column(name = "id_voiture")
    int idVoiture;
    double consommation;
    double kilometrage;
    int etat;
    @Column(name = "id_couleur")
    int idCouleur;
    @Column(name = "nom_couleur")
    String nomCouleur;
    String hexa;

    @Column(name = "id_modele")
    int idModele;
    @Column(name = "nom_modele")
    String nomModele;
    @Column(name = "nb_place")
    int nbPlace;
    @Column(name = "nb_porte")
    int nbPorte;
    @Column(name = "annee_sortie")
    int anneeSortie;

    @Column(name = "id_categorie")
    int idCategorie;
    @Column(name = "nom_categorie")
    String nomCategorie;

    @Column(name = "id_marque")
    int idMarque;
    @Column(name = "nom_marque")
    String nomMarque;
    String logo;

    @Column(name = "utilisateur_nom")
    String utilisateurNom;
    @Column(name = "utilisateur_prenom")
    String utilisateurPrenom;
    @Column(name = "date_inscription")
    LocalDate dateInscription;

    @OneToMany(mappedBy = "annonceGeneral", cascade = CascadeType.PERSIST)
    List<AnnoncePhoto> photos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getNbVues() {
        return nbVues;
    }

    public void setNbVues(int nbVues) {
        this.nbVues = nbVues;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(int idCouleur) {
        this.idCouleur = idCouleur;
    }

    public String getNomCouleur() {
        return nomCouleur;
    }

    public void setNomCouleur(String nomCouleur) {
        this.nomCouleur = nomCouleur;
    }

    public String getHexa() {
        return hexa;
    }

    public void setHexa(String hexa) {
        this.hexa = hexa;
    }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
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

    public void setAnneeSortie(int anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    public void setUtilisateurPrenom(String utilisateurPrenom) {
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public List<AnnoncePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<AnnoncePhoto> photos) {
        this.photos = photos;
    }

}
