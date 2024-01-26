package com.cloud.voiture.repositories.notification;

import java.util.List;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.notification.UtilisateurToken;

public interface UtilisateurTokenRepository extends GenericRepository<UtilisateurToken> {
    List<UtilisateurToken> findByIdUtilisateur(int idUtilisateur);
}
