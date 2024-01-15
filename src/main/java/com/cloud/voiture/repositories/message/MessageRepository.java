package com.cloud.voiture.repositories.message;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloud.voiture.models.message.Message;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByExpediteurIdAndDestinataireId(int expediteurId, int destinataireId);
}
