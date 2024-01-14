package com.cloud.voiture.models.auth;

import java.time.LocalDate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cloud.voiture.crud.model.GenericModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Utilisateur extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull(message = "")
    @NotBlank(message = "")
    String nom;

    @NotNull(message = "")
    @NotBlank(message = "")
    String prenom;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Email(message = "Cet email n'est pas valide.")
    String email;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Column(name = "mot_de_passe")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @Column(insertable = false)
    LocalDate dateInscription;

    @NotNull(message = "")
    @NotBlank(message = "")
    String adresse;

    @Column(name = "id_role")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    int idRole;

    @ManyToOne
    @JoinColumn(name = "id_role", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
