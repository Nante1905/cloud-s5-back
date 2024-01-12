package com.cloud.voiture.models.customPagination;

import org.springframework.stereotype.Component;

@Component
public class CustomPagination {
    int numero;
    int taillePage;

    public CustomPagination() {
        setNumero(1);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTaillePage() {
        return taillePage;
    }

    public void setTaillePage(int taillePage) {
        this.taillePage = taillePage;
    }

}
