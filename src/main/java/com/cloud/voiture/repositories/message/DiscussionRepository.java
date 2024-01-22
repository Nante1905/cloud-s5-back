package com.cloud.voiture.repositories.message;

import com.cloud.voiture.models.message.Discussion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DiscussionRepository extends MongoRepository<Discussion, String> {

    Discussion findByIdDiscussion(String idDiscussion);
}
