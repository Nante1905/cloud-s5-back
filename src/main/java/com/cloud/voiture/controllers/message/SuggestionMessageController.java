package com.cloud.voiture.controllers.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

@Secured({ "ADMIN" })
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Une erreur s'est produite"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getSuggestionMessages() {
        try {
            List<SuggestionMessage> body = service.findAllSuggestionMessages();
            return ResponseEntity.ok().body(new Response(body, ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Erreur lors du chargement des messages."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSuggestionMessage(@PathVariable String id) {
        try {
            SuggestionMessage body = service.getSuggestionMessageById(id);
            return ResponseEntity.ok().body(new Response(body, ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Erreur lors de la récupération du message"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifySuggestionMessage(@RequestBody SuggestionMessage suggestionMessage,
            @PathVariable("id") String id) {
        try {
            suggestionMessage.setId(id);
            SuggestionMessage message = service.updateSuggestionMessage(suggestionMessage);
            return ResponseEntity.ok().body(new Response(message, "Modifier avec succes"));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Aucune suggestion messaget cet id."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Erreur lors de la modification"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSuggestionMessage(@PathVariable String id) {
        try {
            String data = service.deleteSuggestionMessage(id);
            return ResponseEntity.ok().body(new Response(data, "Supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }
}
