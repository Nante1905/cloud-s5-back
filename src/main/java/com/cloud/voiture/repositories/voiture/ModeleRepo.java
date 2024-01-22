package com.cloud.voiture.repositories.voiture;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.voiture.Modele;

public interface ModeleRepo extends GenericRepository<Modele> {
    @Override
    @Query(value = "select * from v_modele_marque order by nom_marque asc", nativeQuery = true)
    public List<Modele> findAll();
}
