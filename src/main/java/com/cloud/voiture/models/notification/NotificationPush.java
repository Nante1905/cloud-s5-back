package com.cloud.voiture.models.notification;

import java.util.List;

public class NotificationPush {
    String titre;
    String contenu;
    List<String> token;

    public NotificationPush() {
    }

    public NotificationPush(String titre, String contenu, List<String> token) {
        this.titre = titre;
        this.contenu = contenu;
        this.token = token;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public List<String> getToken() {
        return token;
    }

    public void setToken(List<String> token) {
        this.token = token;
    }

}
