package com.cloud.voiture.repositories.annonce;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.Annonce;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface AnnonceRepository extends GenericRepository<Annonce> {
    @Query(value ="select * from v_annonce_non_valide", nativeQuery = true)
    List<Annonce> getAllNonValide();
}
