package com.cloud.voiture.services.voiture;

import java.util.List;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cloud.voiture.crud.pagination.Paginated;
import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.models.voiture.Etat;
import com.cloud.voiture.repositories.voiture.EtatRepo;

@Service
public class EtatService extends GenericService<Etat> {
    @Autowired
    EtatRepo etatRepo;

    @Override
    public List<Etat> findAll() {
        return etatRepo.findAll(Sort.by("valeur").ascending());
    }

    @Override
    public Paginated<Etat> findAll(int nbPage, int pageSize) {
        Pageable pageable = PageRequest.of(nbPage - 1, pageSize, Sort.by("valeur").ascending());
        Page<Etat> page = etatRepo.findAll(pageable);

        return new Paginated<Etat>(
                page.getContent(),
                page.getTotalPages(),
                page.getNumber() + 1);
    }
}
