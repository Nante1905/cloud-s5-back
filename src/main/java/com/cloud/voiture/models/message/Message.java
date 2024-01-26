package com.cloud.voiture.models.message;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private String messageId;
    private int expediteurId;
    private int destinataireId;
    private String idDiscussion;
    private String contenu;
    private LocalDateTime dateEnvoi = LocalDateTime.now();
    private int type;
}
