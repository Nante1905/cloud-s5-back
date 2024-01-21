package com.cloud.voiture.models.message;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cloud.voiture.models.auth.Utilisateur;

@Document(collection = "discussions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discussion {
    @Id
    private String idDiscussion;
    @Field("userId1")
    private int userId1;
    @Field("userId2")
    private int userId2;

    @Transient
    List<Message> messages;
    @Transient
    Utilisateur gauche;
    @Transient
    Utilisateur droite;

    
    public Discussion(List<Message> messages, Utilisateur gauche, Utilisateur droite) {
        setMessages(messages);
        setDroite(droite);
        setGauche(gauche);
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
