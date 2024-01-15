package com.cloud.voiture.models.message;

import java.util.List;

import com.cloud.voiture.models.auth.Utilisateur;
public class Discussion {
    List<Message> messages;
    Utilisateur gauche;
    Utilisateur droite;

    
    public Discussion(List<Message> messages, Utilisateur gauche, Utilisateur droite) {
        setMessages(messages);
        setDroite(droite);
        setGauche(gauche);
    }
    public Discussion() {
    }
    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public Utilisateur getGauche() {
        return gauche;
    }
    public void setGauche(Utilisateur gauche) {
        this.gauche = gauche;
    }
    public Utilisateur getDroite() {
        return droite;
    }
    public void setDroite(Utilisateur droite) {
        this.droite = droite;
    }
}
