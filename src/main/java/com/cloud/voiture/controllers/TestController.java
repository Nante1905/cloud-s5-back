package com.cloud.voiture.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(new Response("test works", ""));
    }
}
