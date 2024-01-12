package com.cloud.voiture.models.statistique;

import com.cloud.voiture.models.customPagination.CustomPagination;

public class StatRequest {
    int mois;
    int annee;
    CustomPagination pagination;

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public CustomPagination getPagination() {
        return pagination;
    }

    public void setPagination(CustomPagination pagination) {
        this.pagination = pagination;
    }

}
