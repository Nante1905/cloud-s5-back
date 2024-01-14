package com.cloud.voiture.crud.controller;

import java.util.HashMap;

public class SqlErrorMessage {
    public static HashMap<String, String> ERROR_MESSAGE = new HashMap<String, String>() {
        {
            put("23505", "%column% doit être unique. ");
            put("23503", "Il n'existe aucun %column% avec cette valeur.");
            put("23502", "%column% est obligatoire.");
        };
    };

    public static String getMessage(String sqlCode, String column, String table) {
        String error = SqlErrorMessage.ERROR_MESSAGE.get(sqlCode);
        if (error != null && column != null) {
            error = error.replaceAll("%column%", column).replaceAll("%table%", table);
        } else {
            error = "Contrainte de donnée violée";
        }
        return error;
    }
}
