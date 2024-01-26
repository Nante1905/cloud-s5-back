package com.cloud.voiture.repositories.message;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cloud.voiture.models.message.LastMessage;

@Repository
public interface LastMessageRepository extends MongoRepository<LastMessage, String> {
    List<LastMessage> findAll();
}
