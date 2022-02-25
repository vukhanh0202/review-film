package com.citynow.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manage Validate Utility
 * @author VuKhanh
 */
public class ValidateUtil {

    /**
     * Check string suitable regex
     * @param regex
     * @param string
     * @return boolean
     */
    public static boolean validate(String regex, String string){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
