package com.cloud.voiture.repositories.annonce;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.Annonce;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnonceRepository extends GenericRepository<Annonce> {
  @Query(value = "select * from v_annonce_non_valide", nativeQuery = true)
  List<Annonce> getAllNonValide();

  @Query(value="select * from annonce where id in (:sql) ", nativeQuery=true)
  List<Annonce> findComplex(@Param("sql") String sql);
}
