package com.cloud.voiture.models.notification;

import com.cloud.voiture.crud.model.GenericModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "utilisateur_notif_token")
public class UtilisateurToken extends GenericModel {
    @Min(value = 1, message = "L'utilisateur est obligatoire")
    @Column(name = "id_utilisateur")
    int idUtilisateur;
    @NotNull(message = "")
    @NotBlank(message = "")
    String token;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
