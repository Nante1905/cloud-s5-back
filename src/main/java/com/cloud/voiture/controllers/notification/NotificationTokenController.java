package com.cloud.voiture.controllers.notification;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.notification.UtilisateurToken;

@RestController
@RequestMapping("/notif-token")
public class NotificationTokenController extends GenericController<UtilisateurToken> {

}
