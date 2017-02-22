package com.nguyen.bzc.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class TextUtils {

    public static String upperCaseFirstLetter(String inputString) {
        if (inputString != null && inputString.length() != 0) {
            return inputString.substring(0, 1).toUpperCase() + inputString.substring(1);
        } else {
            return inputString;
        }

    }


    public static void sortAList(ArrayList<String> list) {
        Collections.sort(list, new StringComparator());
    }

    /*
    Classe pour comparer 2 Strings
    Cette classe est pour aider a arranger une liste de String
     */
    private static class StringComparator implements Comparator<String> {

        public int compare(String first, String second) {

            return first.compareTo(second);
        }
    }

    public static String getKeyFromValue(Hashtable<String, String> table, String value) {
        String key = "";
        for (Map.Entry entry : table.entrySet()) {
            if (value.equals(entry.getValue())) {
                key = (String) entry.getKey();
                break; //breaking because its one to one map
            }
        }
        return key;
    }
}
