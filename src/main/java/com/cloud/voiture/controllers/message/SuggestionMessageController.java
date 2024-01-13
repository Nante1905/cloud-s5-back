package com.cloud.voiture.controllers.message;

import com.cloud.voiture.crud.controller.GenericController;
import com.cloud.voiture.models.message.SuggestionMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suggestionMessages")
public class SuggestionMessageController
  extends GenericController<SuggestionMessage> {}
