package com.cloud.voiture.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.services.authentication.AuthenticationService;
import com.cloud.voiture.types.auth.AuthModel;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthModel body) throws Exception {
        try {
            String token = this.authenticationService.login(body.getEmail(), body.getPassword());

            return ResponseEntity.ok(new Response(token, "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new Response(e.getMessage()));
        }
    }
}
