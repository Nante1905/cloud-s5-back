package com.cloud.voiture.services.message;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.message.SuggestionMessage;
import com.cloud.voiture.repositories.message.SuggestionMessageRepository;

@Service
public class SuggestionMessageService {
    @Autowired
    private SuggestionMessageRepository repository;

    public SuggestionMessage addSuggestionMessage(SuggestionMessage suggestionMessage) {
        suggestionMessage.setId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(suggestionMessage);
    }

    public List<SuggestionMessage> findAllSuggestionMessages() {
        return repository.findAll();
    }

    public SuggestionMessage getSuggestionMessageById(String id) {
        return repository.findById(id).orElse(null);
    }

    public SuggestionMessage updateSuggestionMessage(SuggestionMessage suggestionMessageRequest)
            throws NotFoundException {
        SuggestionMessage existingSuggestionMessage = repository.findById(suggestionMessageRequest.getId())
                .orElseThrow(() -> new NotFoundException());

        existingSuggestionMessage.setContenu(suggestionMessageRequest.getContenu());
        return repository.save(existingSuggestionMessage);

    }

    public String deleteSuggestionMessage(String id) {
        repository.deleteById(id);
        return id + " suggestion message deleted.";
    }
}
