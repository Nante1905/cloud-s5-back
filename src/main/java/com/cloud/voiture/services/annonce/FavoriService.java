package com.cloud.voiture.services.annonce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.models.annonce.Favori;
import com.cloud.voiture.repositories.annonce.FavoriRepository;

@Service
public class FavoriService {
    @Autowired
    FavoriRepository favoriRepository;

    public void save(Favori f) {
        favoriRepository.save(f);
    }
}
