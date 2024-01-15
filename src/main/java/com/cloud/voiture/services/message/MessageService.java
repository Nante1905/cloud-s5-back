package com.cloud.voiture.services.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.message.Message;
import com.cloud.voiture.repositories.message.MessageRepository;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    
    public Message addMessage(Message message) {
        message.setMessageId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(message);
    }

    public List<Message> findAllMessages() {
        return repository.findAll();
    }

    public List<Message> findMessagesByUsers(int expediteurId, int destinataireId) {
    Criteria criteria1 = Criteria.where("expediteurId").is(expediteurId).and("destinataireId").is(destinataireId);
    Criteria criteria2 = Criteria.where("expediteurId").is(destinataireId).and("destinataireId").is(expediteurId);
    
    Query query = new Query(new Criteria().orOperator(criteria1, criteria2));

    return mongoTemplate.find(query, Message.class);
}
}
