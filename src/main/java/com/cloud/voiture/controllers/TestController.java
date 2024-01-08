package com.cloud.voiture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.services.stats.StatistiqueService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StatistiqueService statistiqueService;

    @GetMapping("")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(new Response("test works", ""));
    }

    @GetMapping("/stat")
    public ResponseEntity<?> stat() {
        return ResponseEntity.ok().body(new Response(statistiqueService.getNbVendu(2, 2023), "stat works"));
    }
}
