package com.cloud.voiture.repositories.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cloud.voiture.models.message.Message;

import java.util.List;
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByExpediteurIdAndDestinataireId(int expediteurId, int destinataireId);
}
