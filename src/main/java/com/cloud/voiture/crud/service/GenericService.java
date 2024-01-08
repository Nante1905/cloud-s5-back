package com.cloud.voiture.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.crud.repository.GenericRepository;

public class GenericService<T extends GenericModel> {
    @Autowired
    GenericRepository<T> repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T find(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    public T save(T model) {
        return repository.save(model);
    }

    public T update(T model, int id) {
        model.setId(id);
        return repository.save(model);
    }

    public GenericRepository<T> getRepository() {
        return repository;
    }
}
