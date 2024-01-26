package com.cloud.voiture.services.message;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    public List<Utilisateur> findUsersByIdDiscussion(String idDiscussion) {
        Discussion discussion = repository.findByIdDiscussion(idDiscussion);
        Utilisateur gauche = discussion.getGauche();
        Utilisateur droite = discussion.getDroite();
        return List.of(gauche, droite);
    }

    public boolean allowed(String idDiscussion, int userId) {
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
