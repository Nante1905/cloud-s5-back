package com.cloud.voiture.services.voiture;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.statistique.MarqueBenefice;
import com.cloud.voiture.models.voiture.Marque;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class MarqueService extends GenericService<Marque> {

}
