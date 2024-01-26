package com.cloud.voiture.controllers.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.models.message.LastMessage;
import com.cloud.voiture.services.message.LastMessageService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/lastmessages")
public class LastMessageController {

    @Autowired
    private LastMessageService lastMessageService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response> getDiscussionLastMessageById(@PathVariable int userId) {
        // System.out.println(lastMessageService.findAll() );
        List<LastMessage> lastMessages = lastMessageService.getDiscussionLastMessageById(userId);
        return ResponseEntity.ok(new Response(lastMessages, "Last messages for user with id: " + userId));
    }
}
