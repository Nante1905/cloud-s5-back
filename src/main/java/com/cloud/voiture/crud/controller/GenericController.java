package com.cloud.voiture.crud.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cloud.voiture.crud.model.GenericModel;
import com.cloud.voiture.crud.service.GenericService;
import com.cloud.voiture.types.response.Response;

public class GenericController<T extends GenericModel> {

    @Autowired
    GenericService<T> service;

    HashMap<String, Object> res;
    HashMap<String, Object> err;

    public GenericController() {
        res = new HashMap<>();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<T> results = service.findAll();
            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") int id) {
        try {
            T results = service.find(id);
            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody T model) {
        try {
            T results = service.save(model);
            return ResponseEntity.ok(new Response(results, "Inserer avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> save(@RequestBody T model, @PathVariable(name = "id") int id) {
        try {
            T results = service.update(model, id);
            return ResponseEntity.ok(new Response(results, "Modifier avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    public GenericService<T> getService() {
        return service;
    }

    public HashMap<String, Object> getRes() {
        return res;
    }

    public HashMap<String, Object> getErr() {
        return err;
    }
}
