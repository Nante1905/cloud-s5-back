package com.cloud.voiture.models.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private String messageId;
    private int expediteurId;
    private int destinataireId;
    private String contenu;
    private Date dateEnvoi;
    private int type;
}
