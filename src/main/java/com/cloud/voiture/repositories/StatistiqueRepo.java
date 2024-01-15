package com.cloud.voiture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cloud.voiture.models.voiture.Couleur;

public interface StatistiqueRepo extends JpaRepository<Couleur, Integer> {
  @Query(value = "select m.mois, coalesce(sum(v.commission), 0) commission\r\n" + //
      "from v_mois m\r\n" + //
      "left outer join \r\n" + //
      "    (select * \r\n" + //
      "    from v_vente \r\n" + //
      "    where extract(month from date_vente) = ?1 and extract(year from date_vente) = ?2) v \r\n" + //
      "on m.mois = extract(month from v.date_vente)\r\n" + //
      "group by m.mois\r\n" + //
      "order by m.mois", nativeQuery = true)
  public List<Object[]> getBeneficeParMois(int mois, int annee);

  @Query(value = "select count(*) from annonce_vendu \r\n" + //
      "where extract(month from date_vente) = ?1 and extract(year from date_vente) = ?2", nativeQuery = true)
  public int getNbVendu(int mois, int annee);

  @Query(value = "select count(*) from annonce where extract(month from date_vente) = ?1 and extract(year from date_vente) = ?2", nativeQuery = true)
  public int getNbAnnonce(int mois, int annee);

  @Query(value = "select avg(a.date_creation - av.date_vente)\r\n" + //
      "from annonce_vendu av\r\n" + //
      "join annonce a \r\n" + //
      "on av.id_annonce = a.id\r\n" + //
      "where extract(month from date_vente) = ?1 and extract(year from date_vente) = ?2", nativeQuery = true)
  public int getAvgCreationVente(int mois, int annee);

  @Query(value = "select coalesce(sum(commission), 0) commission from annonce_vendu av join annonce a on av.id_annonce = a.id where extract(month from date_vente) = ?1 and extract(year from date_vente) = ?2", nativeQuery = true)
  public double getTotalBenefice(int mois, int annee);

}
