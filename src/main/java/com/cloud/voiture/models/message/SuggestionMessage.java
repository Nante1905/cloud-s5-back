package com.cloud.voiture.models.message;

import com.cloud.voiture.crud.model.GenericModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "suggestion_message")
public class SuggestionMessage extends GenericModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String contenu;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContenu() {
    return contenu;
  }

  public void setContenu(String contenu) {
    this.contenu = contenu;
  }
}
