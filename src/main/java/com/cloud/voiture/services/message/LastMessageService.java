package com.cloud.voiture.services.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.message.LastMessage;
import com.cloud.voiture.repositories.message.LastMessageRepository;

@Service
public class LastMessageService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private LastMessageRepository lastMessageRepository;

    public List<LastMessage> getDiscussionLastMessageById(int userId) {
        System.out.println(lastMessageRepository.findAll());
        Criteria c1 = Criteria.where("userId1").is(userId);
        Criteria c2 = Criteria.where("userId2").is(userId);
        Query query = new Query( new Criteria().orOperator(c1,c2) );
        System.out.println( query );
        System.out.println( mongoTemplate.find(query, LastMessage.class) );
        return mongoTemplate.find(query, LastMessage.class);
    }


}
