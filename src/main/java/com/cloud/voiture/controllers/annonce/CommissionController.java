package com.cloud.voiture.controllers.annonce;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.annonce.Annonce;
import com.cloud.voiture.models.annonce.Commission;
import com.cloud.voiture.services.annonce.CommissionService;
import com.cloud.voiture.types.response.Response;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commissions")
public class CommissionController extends GenericController<Commission> {
    @Autowired
    private CommissionService commissionService;
     @GetMapping("/now")
  public ResponseEntity<Response> findNonValide() {
    try {
      Commission current = commissionService.getLast();
      return ResponseEntity.ok(new Response(current, ""));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new Response(e.getMessage()));
    }
  }
}
