package com.cloud.voiture.repositories.annonce;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.Annonce;

import jakarta.transaction.Transactional;

public interface AnnonceRepository extends GenericRepository<Annonce> {
    @Modifying
    @Query(nativeQuery = true, value = "update annonce set status = ?2 where id = ?1 ")
    public void updateStatus(int idAnnonce, int status);
}
