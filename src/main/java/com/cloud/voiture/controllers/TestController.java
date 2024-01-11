package com.cloud.voiture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.services.authentication.AuthenticationService;
import com.cloud.voiture.services.stats.StatistiqueService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StatistiqueService statistiqueService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("")
    public ResponseEntity<?> test() throws Exception {
        String token = this.authenticationService.login("nante@nante.com", "nante");
        return ResponseEntity.ok().body(new Response(token, ""));
    }

    @GetMapping("/stat")
    public ResponseEntity<?> stat() {
        return ResponseEntity.ok().body(new Response(statistiqueService.getNbVendu(2, 2023), "stat works"));
    }
}
