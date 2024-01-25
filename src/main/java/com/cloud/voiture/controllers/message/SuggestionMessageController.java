package com.cloud.voiture.controllers.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.models.message.SuggestionMessage;
import com.cloud.voiture.services.message.SuggestionMessageService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/suggestion_messages")
public class SuggestionMessageController {
    @Autowired
    private SuggestionMessageService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createSuggestionMessage(@RequestBody SuggestionMessage suggestionMessage) {
        try {
            SuggestionMessage body = service.addSuggestionMessage(suggestionMessage);
            return ResponseEntity.ok().body(new Response(body, "Inserer avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getSuggestionMessages() {
        try {
            List<SuggestionMessage> body = service.findAllSuggestionMessages();
            return ResponseEntity.ok().body(new Response(body, ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSuggestionMessage(@PathVariable String id) {
        try {
            SuggestionMessage body = service.getSuggestionMessageById(id);
            return ResponseEntity.ok().body(new Response(body, ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifySuggestionMessage(@RequestBody SuggestionMessage suggestionMessage,
            @PathVariable("id") String id) {
        try {
            suggestionMessage.setId(id);
            SuggestionMessage message = service.updateSuggestionMessage(suggestionMessage);
            return ResponseEntity.ok().body(new Response(message, "Modifier avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSuggestionMessage(@PathVariable String id) {
        try {
            String data = service.deleteSuggestionMessage(id);
            return ResponseEntity.ok().body(new Response(data, "Supprimer avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }
}
