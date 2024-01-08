package com.cloud.voiture.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.voiture.crud.model.GenericModel;

public interface GenericRepository<T extends GenericModel> extends JpaRepository<T, Integer> {

}
