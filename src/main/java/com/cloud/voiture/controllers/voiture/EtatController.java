package com.cloud.voiture.controllers.voiture;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.voiture.Etat;
import com.cloud.voiture.services.utilities.Utilities;
import com.cloud.voiture.services.voiture.EtatService;
import com.cloud.voiture.types.response.Response;

@RestController
@RequestMapping("/etats")
public class EtatController extends GenericController<Etat> {

}
