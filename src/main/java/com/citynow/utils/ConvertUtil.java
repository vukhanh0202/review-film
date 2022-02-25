package com.citynow.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Manage Convert Utility
 * @author VuKhanh
 */
public class ConvertUtil {

    /**
     * Convert Json to String
     * @param reader
     * @return String(Json)
     */
    public static String convertJsonToString(BufferedReader reader){
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return sb.toString();
    }
}
