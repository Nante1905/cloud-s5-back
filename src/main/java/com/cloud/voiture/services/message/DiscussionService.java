package com.cloud.voiture.services.message;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.cloud.voiture.chat.requests.CreatePrivateChatRequest;
import com.cloud.voiture.models.auth.Utilisateur;
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

            
    public List<Discussion> getDiscussionsWithLastMessagesForUser(int userId) {
        // Match discussions for the user
        Criteria criteria1 = Criteria.where("userId1").is(userId);
        Criteria criteria2 = Criteria.where("userId2").is(userId);
        Criteria matchCriteria = new Criteria().orOperator(criteria1, criteria2);
        AggregationOperation matchOperation = Aggregation.match(matchCriteria);

        // Perform the join between Discussion and Message based on idDiscussion
        AggregationOperation lookupOperation = Aggregation.lookup("message", "idDiscussion", "idDiscussion", "messages");

        // Unwind the array created by the lookup
        AggregationOperation unwindOperation = Aggregation.unwind("$messages", true);

        // Sort messages within each discussion by dateEnvoi in descending order
        AggregationOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "messages.dateEnvoi");

        // Group messages by idDiscussion and take the first message in each group (latest message)
        AggregationOperation groupOperation = Aggregation.group("idDiscussion")
                .last("userId1").as("userId1")
                .last("userId2").as("userId2")
                .last("messages").as("lastMessage");

        // Optionally, project fields to shape the output
        AggregationOperation projectOperation = Aggregation.project("userId1", "userId2", "lastMessage");

        // Perform the aggregation
        TypedAggregation<Discussion> aggregation = Aggregation.newAggregation(Discussion.class, matchOperation, lookupOperation, unwindOperation, sortOperation, groupOperation, projectOperation);

        AggregationResults<Discussion> results = mongoTemplate.aggregate(aggregation, Discussion.class);

        // Extract the results
        List<Discussion> discussions = results.getMappedResults();

        return discussions;
    }
     public List<Utilisateur> findUsersByIdDiscussion(String idDiscussion) {
        Discussion discussion = repository.findByIdDiscussion(idDiscussion);
        Utilisateur gauche = discussion.getGauche();
        Utilisateur droite = discussion.getDroite();
        return List.of(gauche, droite);
    }
    public boolean allowed(String idDiscussion, int userId){
        Discussion discussion = repository.findByIdDiscussion(idDiscussion);
        return discussion.getUserId1() == userId || discussion.getUserId2() == userId;
    }

    public Discussion save(CreatePrivateChatRequest request, int contacter) {
        
        Discussion discussion = new Discussion();
        discussion.setUserId1(contacter);
        discussion.setUserId2(request.getTargetUserId());
        discussion.setIdDiscussion(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(discussion);
    }
}
