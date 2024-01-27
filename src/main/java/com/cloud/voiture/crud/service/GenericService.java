package com.cloud.voiture.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.crud.pagination.Paginated;
import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.exceptions.ValidationException;

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

  public T update(T model, int id) throws NotFoundException, ValidationException {
    find(id);
    model.setId(id);
    return repository.save(model);
  }

  public void delete(int id) throws NotFoundException {
    T model = find(id);
    repository.delete(model);
    System.out.println("deleted =========================");
  }

  public GenericRepository<T> getRepository() {
    return repository;
  }

  public Paginated<T> findAll(int nbPage, int pageSize) {
    Pageable pageable = PageRequest.of(nbPage - 1, pageSize);
    Page<T> page = repository.findAll(pageable);
    return new Paginated<T>(
        page.getContent(),
        page.getTotalPages(),
        page.getNumber() + 1);
  }
}
