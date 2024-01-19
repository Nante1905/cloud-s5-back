package com.cloud.voiture.repositories.annonce;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.Annonce;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnonceRepository extends GenericRepository<Annonce> {
  @Query(value = "select * from annonce where id_utilisateur = :utilisateur", nativeQuery = true)
  List<Annonce> findByUser(@Param("utilisateur") int idUtilisateur);

  @Modifying
  @Query(nativeQuery = true, value = "update annonce set status = ?2 where id = ?1 ")
  public void updateStatus(int idAnnonce, int status);

  @Query(value = "select count(annonce.id) from annonce where cast(date_creation as date) =  cast(current_date as date)", nativeQuery = true)

  int getNumOfTheDay();

  @Query(value = "select * from v_annonce_non_valide", nativeQuery = true)
  List<Annonce> getAllNonValide();

  @Query(value = "select * from v_annonce_non_valide order by date_creation desc limit :taille offset(:page - 1)*:taille", nativeQuery = true)
  List<Annonce> getAllNonValide(int page, int taille);

  @Query(value = "select * from annonce where id in (:sql) ", nativeQuery = true)
  List<Annonce> findComplex(@Param("sql") String sql);

  @Modifying
  @Query(value = "update annonce set nb_vue = nb_vue+1 where id = :id", nativeQuery = true)
  int addView(@Param("id") int id);
}
