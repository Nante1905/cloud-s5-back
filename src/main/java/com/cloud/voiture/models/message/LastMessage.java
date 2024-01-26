package com.cloud.voiture.models.message;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cloud.voiture.models.auth.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "v_latest_messages")
public class LastMessage {
    @Id
    private String id;
    @Field("userId1")
    private int userId1;
    @Field("userId2")
    private int userId2;
    
    @Transient
    Utilisateur gauche;
    @Transient
    Utilisateur droite;

    private Message lastMessage;
}
