package com.cloud.voiture.services.message;

import com.cloud.voiture.models.auth.Utilisateur;
import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.repositories.message.DiscussionRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Discussion> getDiscussionsForUser(int userId) {
        System.out.println("tonga ato");
        Criteria criteria1 = Criteria.where("userId1").is(userId);
        Criteria criteria2 = (Criteria.where("userId2").is(userId));
        Query query = new Query(new Criteria().orOperator( criteria1 , criteria2 ));
        System.out.println(query);
        System.out.println(mongoTemplate.find(query, Discussion.class));
        return mongoTemplate.find(query, Discussion.class);
    }

     public List<Utilisateur> findUsersByIdDiscussion(String idDiscussion) {
        Discussion discussion = repository.findByIdDiscussion(idDiscussion);
        Utilisateur gauche = discussion.getGauche();
        Utilisateur droite = discussion.getDroite();
        return List.of(gauche, droite);
    }

    public Discussion addDiscussion(Discussion discussion) {
        discussion.setIdDiscussion(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(discussion);
    }
}
