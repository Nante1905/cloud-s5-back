package com.cloud.voiture.models.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "suggestion_messages") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionMessage {
    @Id
    private String id;
    private String contenu;
}
