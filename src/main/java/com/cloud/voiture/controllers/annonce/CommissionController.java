package com.cloud.voiture.controllers.annonce;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.annonce.Commission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commissions")
public class CommissionController extends GenericController<Commission> {}
