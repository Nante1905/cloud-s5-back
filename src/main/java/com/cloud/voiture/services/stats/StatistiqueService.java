package com.cloud.voiture.services.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.voiture.repositories.voiture.StatistiqueRepo;

@Service
public class StatistiqueService {

    @Autowired
    private StatistiqueRepo statistiqueRepo;

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
