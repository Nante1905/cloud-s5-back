package com.cloud.voiture.repositories.annonce;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.models.annonce.favori.Favori;
import com.cloud.voiture.models.annonce.favori.FavoriAnnonceID;

public interface FavoriRepository extends JpaRepository<Favori, FavoriAnnonceID> {

    @Modifying
    @Query(value = "insert into annonce_favori(id_utilisateur, id_annonce, date_ajout) values (:user, :annonce, now())", nativeQuery = true)
    public void insert(int user, int annonce);

    public Optional<Favori> findById(FavoriAnnonceID id);

    @Query(value = "select a.id id_annonce, ?1 id_utilisateur, f.date_ajout from v_annonce_gen_valide a left join annonce_favori f on f.id_annonce = a.id  and f.id_utilisateur = ?1 where id = ?2", nativeQuery = true)
    public Optional<Favori> existsOrLiked(int user, int annonce);
}
