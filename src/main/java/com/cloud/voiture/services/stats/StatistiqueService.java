package com.cloud.voiture.services.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.config.Constant;
import com.cloud.voiture.models.customPagination.CustomPagination;
import com.cloud.voiture.models.statistique.MarqueBenefice;
import com.cloud.voiture.models.statistique.StatRequest;
import com.cloud.voiture.repositories.voiture.StatistiqueRepo;
import com.cloud.voiture.services.voiture.MarqueService;

import jakarta.persistence.EntityManager;

@Service
public class StatistiqueService {

    @Autowired
    private StatistiqueRepo statistiqueRepo;
    @Autowired
    private Constant constant;

    public HashMap<String, Object> getBeneficeStatistique(StatRequest params, EntityManager entityManager) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("benefice", this.getTotalBenefice(params.getMois(), params.getAnnee()));
        data.put("beneficeMarque", this.findBeneficeParMarque(params, entityManager));
        return data;
    }

    public List<MarqueBenefice> findBeneficeParMarque(StatRequest params, EntityManager entityManager) {
        String req = "select id_marque, m.nom nom_marque, m.logo, montant from f_benefice_par_marque(:mois, :annee) f join marque m on f.id_marque = m.id order by montant desc, nom_marque asc limit :taille offset(:numero - 1)*:taille";

        if (params.getPagination() == null) {
            System.out.println("Nuull ny pagination");
            CustomPagination p = new CustomPagination();
            p.setTaillePage(constant.getDefaultPageSize());
            params.setPagination(p);
        }

        return entityManager.createNativeQuery(req, MarqueBenefice.class)
                .setParameter("mois", params.getMois())
                .setParameter("annee", params.getAnnee())
                .setParameter("numero", params.getPagination().getNumero())
                .setParameter("taille", params.getPagination().getTaillePage())
                .getResultList();
    }

    public List<HashMap<String, Object>> getBeneficeParMois(int mois, int annee) {
        List<Object[]> benefParMoisObject = statistiqueRepo.getBeneficeParMois(mois, annee);

        List<HashMap<String, Object>> benefParMois = new ArrayList<>();

        for (Object[] obj : benefParMoisObject) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("mois", obj[0]);
            map.put("benefice", obj[1]);
            benefParMois.add(map);
        }

        return benefParMois;
    }

    public int getNbVendu(int mois, int annee) {
        return statistiqueRepo.getNbVendu(mois, annee);
    }

    public int getNbAnnonce(int mois, int annee) {
        return statistiqueRepo.getNbAnnonce(mois, annee);
    }

    // en jours
    public int getAvgCreationVente(int mois, int annee) {
        return statistiqueRepo.getAvgCreationVente(mois, annee);
    }

    public double getTotalBenefice(int mois, int annee) {
        return statistiqueRepo.getTotalBenefice(mois, annee);
    }
}
