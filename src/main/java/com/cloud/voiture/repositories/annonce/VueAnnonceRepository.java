package com.cloud.voiture.repositories.annonce;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.VueAnnonce;

public interface VueAnnonceRepository extends GenericRepository<VueAnnonce> {
    @Query(value = "select id from annonce where id_utilisateur = :user and id_annonce = :annonce ", nativeQuery = true)
    public VueAnnonce getAnnonce(@Param("user") int user, @Param("annonce") int annonce);

    List<VueAnnonce> findByIdUtilisateurAndIdAnnonce(int idUtilisateur, int idAnnonce);
}
