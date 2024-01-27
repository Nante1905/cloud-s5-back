package com.cloud.voiture.models.annonce;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.models.annonce.annoncePhoto.AnnoncePhoto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
    int kilometrage;
    int etat;
    @Column(name = "id_couleur")
    int idCouleur;
    @Column(name = "id_modele")
    int idModele;
    @Column(name = "id_boite_vitesse")
    int idBoiteVitesse;
    @Column(name = "id_energie")
    int idEnergie;

    @Column(name = "nom_couleur")
    String nomCouleur;
    String hexa;

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
    @Column(name = "id_marque")
    int idMarque;

    @Column(name = "nom_vitesse")
    String nomVitesse;
    @Column(name = "nom_energie")
    String nomEnergie;
    @Column(name = "nom_categorie")
    String nomCategorie;

    @Column(name = "nom_marque")
    String nomMarque;
    String logo;

    @Column(name = "utilisateur_nom")
    String utilisateurNom;
    @Column(name = "utilisateur_prenom")
    String utilisateurPrenom;
    @Column(name = "date_inscription")
    LocalDateTime dateInscription;
    String adresse;

    // @OneToMany(mappedBy = "annonceGeneral", cascade = CascadeType.PERSIST)
    @Transient
    List<AnnoncePhoto> photos;

    public AnnonceGeneral(int id, String reference, String description, int status, LocalDateTime dateCreation,
            double prix, double commission, int nbVues, int idUtilisateur, int idVoiture, double consommation,
            int kilometrage, int etat, int idCouleur, int idModele, int idBoiteVitesse, int idEnergie,
            String nomCouleur, String hexa, String nomModele, int nbPlace, int nbPorte, int anneeSortie,
            int idCategorie, int idMarque, String nomVitesse, String nomEnergie, String nomCategorie, String nomMarque,
            String logo, String utilisateurNom, String utilisateurPrenom, LocalDateTime dateInscription, String adresse,
            List<AnnoncePhoto> photos) {
        this.id = id;
        this.reference = reference;
        this.description = description;
        this.status = status;
        this.dateCreation = dateCreation;
        this.prix = prix;
        this.commission = commission;
        this.nbVues = nbVues;
        this.idUtilisateur = idUtilisateur;
        this.idVoiture = idVoiture;
        this.consommation = consommation;
        this.kilometrage = kilometrage;
        this.etat = etat;
        this.idCouleur = idCouleur;
        this.idModele = idModele;
        this.idBoiteVitesse = idBoiteVitesse;
        this.idEnergie = idEnergie;
        this.nomCouleur = nomCouleur;
        this.hexa = hexa;
        this.nomModele = nomModele;
        this.nbPlace = nbPlace;
        this.nbPorte = nbPorte;
        this.anneeSortie = anneeSortie;
        this.idCategorie = idCategorie;
        this.idMarque = idMarque;
        this.nomVitesse = nomVitesse;
        this.nomEnergie = nomEnergie;
        this.nomCategorie = nomCategorie;
        this.nomMarque = nomMarque;
        this.logo = logo;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.dateInscription = dateInscription;
        this.adresse = adresse;
        this.photos = photos;
    }

    public AnnonceGeneral(Integer id, String reference, String description, Integer status, Timestamp dateCreation,
            BigDecimal prix, BigDecimal commission, Integer nbVues, Integer idUtilisateur, Integer idVoiture,
            BigDecimal consommation,
            Integer kilometrage, Integer etat, Integer idCouleur, Integer idModele, Integer idBoiteVitesse,
            Integer idEnergie,
            String nomCouleur, String hexa, String nomModele, Integer nbPlace, Integer nbPorte, Integer anneeSortie,
            Integer idCategorie, Integer idMarque, String nomVitesse, String nomEnergie, String nomCategorie,
            String nomMarque,
            String logo, String utilisateurNom, String utilisateurPrenom, Timestamp dateInscription,
            String adresse) {
        this.id = id;
        this.reference = reference;
        this.description = description;
        this.status = status;
        setDateCreation(dateCreation);
        this.prix = prix.doubleValue();
        this.commission = commission.doubleValue();
        this.nbVues = nbVues;
        this.idUtilisateur = idUtilisateur;
        this.idVoiture = idVoiture;
        this.consommation = consommation.doubleValue();
        this.kilometrage = kilometrage;
        this.etat = etat;
        this.idCouleur = idCouleur;
        this.idModele = idModele;
        this.idBoiteVitesse = idBoiteVitesse;
        this.idEnergie = idEnergie;
        this.nomCouleur = nomCouleur;
        this.hexa = hexa;
        this.nomModele = nomModele;
        this.nbPlace = nbPlace;
        this.nbPorte = nbPorte;
        this.anneeSortie = anneeSortie;
        this.idCategorie = idCategorie;
        this.idMarque = idMarque;
        this.nomVitesse = nomVitesse;
        this.nomEnergie = nomEnergie;
        this.nomCategorie = nomCategorie;
        this.nomMarque = nomMarque;
        this.logo = logo;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        setDateInscription(dateInscription);
        this.adresse = adresse;
    }

    private void setDateInscription(Timestamp d) {
        if (d == null) {
            dateInscription = null;
        } else {
            setDateInscription(d.toLocalDateTime());
        }
    }

    private void setDateCreation(Timestamp d) {
        if (d == null) {
            System.out.println("null lay date de création");
            dateCreation = null;
        } else {
            setDateCreation(d.toLocalDateTime());
        }
    }

    public AnnonceGeneral() {
    }

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

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
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

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public List<AnnoncePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<AnnoncePhoto> photos) {
        this.photos = photos;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdBoiteVitesse() {
        return idBoiteVitesse;
    }

    public void setIdBoiteVitesse(int idBoiteVitesse) {
        this.idBoiteVitesse = idBoiteVitesse;
    }

    public int getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(int idEnergie) {
        this.idEnergie = idEnergie;
    }

    public String getNomVitesse() {
        return nomVitesse;
    }

    public void setNomVitesse(String nomVitesse) {
        this.nomVitesse = nomVitesse;
    }

    public String getNomEnergie() {
        return nomEnergie;
    }

    public void setNomEnergie(String nomEnergie) {
        this.nomEnergie = nomEnergie;
    }

}
