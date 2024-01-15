package com.cloud.voiture.repositories.annonce;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.annonce.HistoriqueAnnonce;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface HistoriqueAnnonceRepo extends GenericRepository<HistoriqueAnnonce> {

    @Query(nativeQuery = true, value = "select * from historique_annonce where id_annonce = ?1 order by date_maj desc")
    public List<HistoriqueAnnonce> findByIdAnnonce(int idAnnonce);
}
