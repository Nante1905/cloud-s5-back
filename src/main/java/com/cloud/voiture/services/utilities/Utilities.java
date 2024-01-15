package com.cloud.voiture.services.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cloud.voiture.crud.model.GenericModel;

import jakarta.persistence.Table;

public class Utilities {

    public static String capitalize(String word) {
        String first = word.substring(0, 1);
        String others = word.substring(1);
        String result = first.toUpperCase() + others;
        return result;
    }

    public static String extractColumnName(Class<?> T, String errorMessage) {
        String tableName = T.getSimpleName();
        Table table = T.getAnnotation(Table.class);
        if (table != null) {
            tableName = table.name();
        }
        System.out.println("tableName " + tableName);

        Pattern pattern = Pattern.compile(tableName.toLowerCase() + "_(.*?)_((?:key)|(?:fkey))");
        Matcher matcher = pattern.matcher(errorMessage);

        if (matcher.find()) {
            System.out.println("column name " + matcher.group(1));
            return matcher.group(1);
        }

        return "entité";
    }
}
