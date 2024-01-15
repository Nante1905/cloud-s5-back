package com.cloud.voiture.controllers.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cloud.voiture.models.message.SuggestionMessage;
import com.cloud.voiture.services.message.SuggestionMessageService;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/suggestion_messages")
public class SuggestionMessageController {
    @Autowired
    private SuggestionMessageService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuggestionMessage createSuggestionMessage(@RequestBody SuggestionMessage suggestionMessage) {
        return service.addSuggestionMessage(suggestionMessage);
    }

    @GetMapping
    public List<SuggestionMessage> getSuggestionMessages() {
        return service.findAllSuggestionMessages();
    }

    @GetMapping("/{id}")
    public SuggestionMessage getSuggestionMessage(@PathVariable String id) {
        return service.getSuggestionMessageById(id);
    }

    @PutMapping
    public SuggestionMessage modifySuggestionMessage(@RequestBody SuggestionMessage suggestionMessage) {
        return service.updateSuggestionMessage(suggestionMessage);
    }

    @DeleteMapping("/{id}")
    public String deleteSuggestionMessage(@PathVariable String id) {
        return service.deleteSuggestionMessage(id);
    }
}
