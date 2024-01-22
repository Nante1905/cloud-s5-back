package com.cloud.voiture.repositories.voiture;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.voiture.Marque;

public interface MarqueRepo extends GenericRepository<Marque> {
    @Override
    @Query(value = "select * from marque order by nom asc", nativeQuery = true)
    public List<Marque> findAll();
}
