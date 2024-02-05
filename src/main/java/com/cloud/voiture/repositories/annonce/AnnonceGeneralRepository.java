package com.cloud.voiture.repositories.annonce;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.AnnonceGeneral;
import com.cloud.voiture.models.annonce.AnnonceValide;

public interface AnnonceGeneralRepository extends GenericRepository<AnnonceGeneral> {
    List<AnnonceGeneral> findByIdUtilisateur(int idUtilisateur);

    @Query(nativeQuery = true, value = "select * from v_annonce_general where id_utilisateur = :user order by date_creation desc limit :taille offset(:page - 1)*:taille")
    List<AnnonceGeneral> findByIdUtilisateur(int user, int page, int taille);

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_non_valide order by date_creation desc")
    public List<AnnonceGeneral> findNonValide();

    @Query(nativeQuery = true, value = "select * from v_annonce_gen_non_valide order by date_creation desc limit :taille offset(:page - 1)*:taille")
    public List<AnnonceGeneral> findNonValide(int page, int taille);

    @Query(nativeQuery = true, value = "select a.*, h.date_maj from v_max_historique_annonce h join v_annonce_general a on h.id_annonce = a.id where h.status = -10 and a.id_utilisateur = :user order by date_maj asc limit :taille offset(:page - 1)*:taille")
    public List<AnnonceValide> findDeleted(int user, int page, int taille);

    @Query(nativeQuery = true, value = "select a.*, h.date_maj from v_max_historique_annonce h join v_annonce_general a on h.id_annonce = a.id where h.status = -10 and a.id_utilisateur = :user order by date_maj asc")
    public List<AnnonceValide> findDeleted(int user);

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
