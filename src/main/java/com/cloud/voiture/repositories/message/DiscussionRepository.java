package com.cloud.voiture.repositories.message;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cloud.voiture.models.message.Discussion;

@Repository
public interface DiscussionRepository extends MongoRepository<Discussion, String> {

    Discussion findByIdDiscussion(String idDiscussion);

    Optional<Discussion> findByUserId1AndUserId2(int userId1, int userId2);
}
