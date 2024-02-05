package com.cloud.voiture.services.message;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.models.message.Discussion;
import com.cloud.voiture.repositories.message.DiscussionRepository;
import com.cloud.voiture.services.UtilisateurService;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private UtilisateurService utilisateurService;

    public List<Integer> findUsersByIdDiscussion(String idDiscussion) {
        Discussion discussion = repository.findByIdDiscussion(idDiscussion);
        // Utilisateur gauche = discussion.getGauche();
        // Utilisateur droite = discussion.getDroite();
        return List.of(discussion.getUserId1(), discussion.getUserId2());
    }

    public boolean allowed(String idDiscussion, int userId) {
        Discussion discussion = repository.findByIdDiscussion(idDiscussion);
        System.out.println(userId + "  " + discussion.getUserId1() + "  " + discussion.getUserId2());
        System.out.println(discussion.getUserId1() == userId || discussion.getUserId2() == userId);
        return discussion.getUserId1() == userId || discussion.getUserId2() == userId;
    }

    public boolean exist(int id1, int id2) {
        Criteria criteria1 = Criteria.where("userId1").is(id1).and("userId2")
                .is(id2);
        Criteria criteria2 = Criteria.where("userId1").is(id2).and("userId2")
                .is(id1);
        Query query = new Query(new Criteria().orOperator(criteria1, criteria2));
        System.out.println(query);
        List<Discussion> discussions_exist = mongoTemplate.find(query, Discussion.class);
        if (discussions_exist.size() != 0)
            return true;
        return false;
    }

    public Discussion save(CreatePrivateChatRequest request, int contacter) throws Exception {
        int id1 = contacter;
        int id2 = request.getTargetUserId();
        if (exist(contacter, request.getTargetUserId())) {
            Criteria criteria1 = Criteria.where("userId1").is(id1).and("userId2")
                    .is(id2);
            Criteria criteria2 = Criteria.where("userId1").is(id2).and("userId2")
                    .is(id1);
            Query query = new Query(new Criteria().orOperator(criteria1, criteria2));
            System.out.println(query);
            List<Discussion> discussions = mongoTemplate.find(query, Discussion.class);
            if (discussions.size() != 0)
                return discussions.get(0);
        }
        if (request.getTargetUserId() == contacter)
            throw new ValidationException("Vous ne pouvez pas créer de discussion avec vous meme");
        Discussion discussion = new Discussion();
        discussion.setUserId1(contacter);
        discussion.setUserId2(request.getTargetUserId());
        discussion.setIdDiscussion(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(discussion);
    }
}
