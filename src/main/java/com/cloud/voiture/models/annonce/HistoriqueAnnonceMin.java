package com.cloud.voiture.models.annonce;

import java.time.LocalDateTime;

public class HistoriqueAnnonceMin {
    LocalDateTime date;
    String status;

    public HistoriqueAnnonceMin() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
