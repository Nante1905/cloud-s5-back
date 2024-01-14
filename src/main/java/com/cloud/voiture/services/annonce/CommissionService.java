package com.cloud.voiture.services.annonce;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.annonce.Commission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class CommissionService extends GenericService<Commission> {

  @PersistenceContext
  EntityManager entityManager;

  public Commission getLast() {
    return (Commission) entityManager
      .createNativeQuery("select * from v_last_commission", Commission.class)
      .getSingleResult();
  }
}
