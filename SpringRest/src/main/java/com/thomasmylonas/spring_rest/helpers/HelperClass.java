package com.thomasmylonas.spring_rest.helpers;

import java.util.Calendar;
import java.util.Date;

/**
 * This is a Helper class which contains public static Java methods for general use.
 *
 * @author Thomas_Mylonas
 * @since 16-May-2022
 */
public abstract class HelperClass {

    public static final String DEFAULT_TIMEZONE = "Europe/Prague"; // "Europe/Budapest", TimeZone.getDefault().getID()

    /**
     * The method checks if a String is "null" or "empty"
     *
     * @param string The String we check if it is "null" or "empty"
     * @return A boolean which checks if a String is "null" or "empty"
     */
    public static boolean isStringNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static Date buildDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day); // cal.set(1972, Calendar.SEPTEMBER, 24);
        return cal.getTime();
    }
}
