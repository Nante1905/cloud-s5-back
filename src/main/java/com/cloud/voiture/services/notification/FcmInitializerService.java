package com.cloud.voiture.services.notification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FcmInitializerService {

    @Value("${app.firebase-conf}")
    private String fcmConfigPath;

    @PostConstruct // ref vita daholo ny injection d atao ito fonction ito
    public void initialize() throws FileNotFoundException, IOException {
        FirebaseOptions configs = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(fcmConfigPath))).build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(configs);
        }
    }
}
