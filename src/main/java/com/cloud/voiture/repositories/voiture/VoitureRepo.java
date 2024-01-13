package com.cloud.voiture.repositories.voiture;

import com.cloud.voiture.crud.repository.GenericRepository;
import com.cloud.voiture.models.voiture.Voiture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoitureRepo extends GenericRepository<Voiture> {
  @Query(
    value = "select  avg(annonce.prix) as prix from annonce join voiture on voiture.id = annonce.id_voiture where voiture.id_modele = :modele and voiture.id_energie=:energie and voiture.id_boite_vitesse=:boitevitesse and etat <= :etatmax or etat >= :etatmin ",
    nativeQuery = true
  )
  double estimate(
    @Param("modele") int idModele,
    @Param("etatmin") int etatMin,
    @Param("etatmax") int etatMax,
    @Param("energie") int energie,
    @Param("boitevitesse") int boiteVitesse
  );
}
