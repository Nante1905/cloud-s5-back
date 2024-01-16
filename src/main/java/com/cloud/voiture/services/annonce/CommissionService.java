package com.cloud.voiture.services.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.annonce.Commission;
import com.cloud.voiture.repositories.annonce.CommissionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CommissionService extends GenericService<Commission> {

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  private CommissionRepository commissionRepository;

  public Commission getLast() {
    return (Commission) entityManager
        .createNativeQuery("select * from v_last_commission", Commission.class)
        .getSingleResult();
  }

  public List<Commission> getAllCommissionSortByLatest() {
    return commissionRepository.findAll(Sort.by("dateAjout").descending());
  }

}
