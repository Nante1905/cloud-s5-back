package com.cloud.voiture.models.annonce.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cloud.voiture.models.annonce.AnnonceEtFavori;
import com.cloud.voiture.models.annonce.AnnonceGeneral;
import com.cloud.voiture.models.annonce.annoncePhoto.AnnoncePhoto;
import com.cloud.voiture.models.auth.UtilisateurDTO;
import com.cloud.voiture.models.voiture.Marque;

public class AnnonceDTO {
    int id;
    Marque marque;
    double prix;
    ModeleDTO modele;
    UtilisateurDTO utilisateur;
    LocalDateTime creation;
    int etat;
    boolean favori;
    List<AnnoncePhoto> photos;

    public AnnonceDTO() {
    }

    public AnnonceDTO(AnnonceGeneral a) {
        setId(a.getId());
        setMarque(new Marque(a.getIdMarque(), a.getNomMarque(), a.getLogo()));
        setModele(new ModeleDTO(a.getIdModele(), a.getNomModele()));
        setUtilisateur(new UtilisateurDTO(a.getIdUtilisateur(), a.getUtilisateurNom(), a.getUtilisateurPrenom(),
                a.getAdresse(), a.getDateInscription()));
        setCreation(a.getDateCreation());
        setEtat(a.getEtat());
        setPrix(a.getPrix());
    }

    public AnnonceDTO(AnnonceEtFavori a) {
        setId(a.getId());
        setMarque(new Marque(a.getIdMarque(), a.getNomMarque(), a.getLogo()));
        setModele(new ModeleDTO(a.getIdModele(), a.getNomModele()));
        setUtilisateur(new UtilisateurDTO(a.getIdUtilisateur(), a.getUtilisateurNom(), a.getUtilisateurPrenom(),
                a.getAdresse(), a.getDateInscription()));
        setCreation(a.getDateCreation());
        setEtat(a.getEtat());
        setPrix(a.getPrix());
        setFavori(a.isFavori());
    }

    public static List<AnnonceDTO> convertAnnonceGeneral(List<AnnonceGeneral> annonces) {
        List<AnnonceDTO> a = new ArrayList<>();
        for (AnnonceGeneral annonce : annonces) {
            a.add(new AnnonceDTO(annonce));
        }
        return a;
    }

    public static List<AnnonceDTO> convertAnnonceEtFavori(List<AnnonceEtFavori> annonces) {
        List<AnnonceDTO> a = new ArrayList<>();
        for (AnnonceEtFavori annonce : annonces) {
            a.add(new AnnonceDTO(annonce));
        }
        return a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public ModeleDTO getModele() {
        return modele;
    }

    public void setModele(ModeleDTO modele) {
        this.modele = modele;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public List<AnnoncePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<AnnoncePhoto> photos) {
        this.photos = photos;
    }

}
