package com.cloud.voiture.services.message;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cloud.voiture.chat.exceptions.UnauthorizedChatting;
import com.cloud.voiture.chat.requests.ChatMessageRequest;
import com.cloud.voiture.models.message.Message;
import com.cloud.voiture.repositories.message.MessageRepository;
import com.cloud.voiture.services.UtilisateurService;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private DiscussionService discussionService;
    
    public Message addMessage(ChatMessageRequest request) throws Exception{
        int iduser = utilisateurService.getAuthenticated().getId();
        if(discussionService.allowed(request.getDiscussionId(), iduser)==false){
            throw new UnauthorizedChatting();
        }
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString().split("-")[0]);
        message.setContenu(request.getMessage());
        message.setExpediteurId(iduser);
        message.setIdDiscussion(request.getDiscussionId());
        return repository.save(message);
    }

    public List<Message> findAllMessages() {
        return repository.findAll();
    }

    public List<Message> findMessagesByidDiscussion(String idDiscussion) {
        Criteria criteria = Criteria.where("idDiscussion").is(idDiscussion);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Message.class);
    }

    public List<Message> findMessagesByUsers(int expediteurId, int destinataireId) {
    Criteria criteria1 = Criteria.where("expediteurId").is(expediteurId).and("destinataireId").is(destinataireId);
    Criteria criteria2 = Criteria.where("expediteurId").is(destinataireId).and("destinataireId").is(expediteurId);
    
    Query query = new Query(new Criteria().orOperator(criteria1, criteria2));

    return mongoTemplate.find(query, Message.class);
}
}
