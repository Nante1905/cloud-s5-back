package com.cloud.voiture.models.statistique;

public class StatTopSellerRequest {
    int mois;
    int annee;
    int toShow;
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
    public int getToShow() {
        return toShow;
    }
    public void setToShow(int toShow) {
        this.toShow = toShow;
    }
    public String getYYYYMM(){
        String strMois = String.valueOf(mois);
        if(mois<10){
            strMois = "0"+strMois;
        }
        return annee+""+strMois;
    }
}
