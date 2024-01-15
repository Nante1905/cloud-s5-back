package com.cloud.voiture.repositories.message;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloud.voiture.models.message.SuggestionMessage;



public interface SuggestionMessageRepository extends MongoRepository<SuggestionMessage, String> {
}
