package com.cloud.voiture.repositories.annonce;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.AnnonceGeneral;

public interface AnnonceGeneralRepository extends GenericRepository<AnnonceGeneral> {
    List<AnnonceGeneral> findByIdUtilisateur(int idUtilisateur);

    @Query(nativeQuery = true, value = "select * from v_annonce_general where id_utilisateur = :user order by date_creation desc limit :taille offset(:page - 1)*:taille")
    List<AnnonceGeneral> findByIdUtilisateur(int user, int page, int taille);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_non_valide order by date_creation desc")
    public List<AnnonceGeneral> findNonValide();

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_non_valide order by date_creation desc limit :taille offset(:page - 1)*:taille")
    public List<AnnonceGeneral> findNonValide(int page, int taille);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_non_valide where id_utilisateur = :user order by date_creation asc limit :taille offset(:page - 1)*:taille")
    public List<AnnonceGeneral> findNonValide(int user, int page, int taille);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_non_valide where id_utilisateur = :user order by date_creation asc")
    public List<AnnonceGeneral> findNonValide(int user);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_valide where id_utilisateur = :user order by date_maj asc limit :taille offset(:page - 1)*:taille")
    public List<AnnonceGeneral> findValide(int user, int page, int taille);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_valide where id_utilisateur = :user order by date_maj asc")
    public List<AnnonceGeneral> findValide(int user);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_vendu where id_utilisateur = :user order by date_maj asc limit :taille offset(:page - 1)*:taille")
    public List<AnnonceGeneral> findVendu(int user, int page, int taille);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_vendu where id_utilisateur = :user order by date_maj asc")
    public List<AnnonceGeneral> findVendu(int user);
}
