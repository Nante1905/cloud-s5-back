package com.cloud.voiture.repositories.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.auth.Utilisateur;

public interface UtilisateurRepository extends GenericRepository<Utilisateur> {
    @Query("SELECT u FROM Utilisateur u join fetch u.role WHERE u.email = :email AND u.password = :password")
    public Optional<Utilisateur> findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM Utilisateur u join fetch u.role WHERE u.email = :email")
    public Optional<Utilisateur> findByEmail(String email);

}
