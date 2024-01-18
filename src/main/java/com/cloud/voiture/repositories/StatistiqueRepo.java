package com.cloud.voiture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.models.voiture.Couleur;

public interface StatistiqueRepo extends JpaRepository<Couleur, Integer> {
    @Query(value = "select * from f_benefice_par_mois(?1, ?2)", nativeQuery = true)
    public List<Object[]> getBeneficeParMois(int mois, int annee);

    @Query(value = "select count(*) from v_annonce_vendu vav where extract(month from vav.date_maj) = 1 and extract(year from vav.date_maj) = 2024", nativeQuery = true)
    public int getNbVendu(int mois, int annee);

    @Query(value = "select count(*) from v_annonce_valide where extract(month from date_maj) = ?1 and extract(year from date_maj) = ?2", nativeQuery = true)
    public int getNbAnnonceValide(int mois, int annee);

    @Query(value = "select coalesce(avg(EXTRACT('days' FROM (av.date_creation - av.date_maj))), 0)\r\n" + //
            "from v_annonce_vendu av\r\n" + //
            "where extract(month from date_maj) = ?1 and extract(year from date_maj) = ?2", nativeQuery = true)
    public int getAvgCreationVente(int mois, int annee);

    @Query(value = "select coalesce(sum(commission), 0) commission from v_annonce_vendu av where extract(month from av.date_maj) = ?1 and extract(year from av.date_maj) = ?2", nativeQuery = true)
    public double getTotalBenefice(int mois, int annee);

}
