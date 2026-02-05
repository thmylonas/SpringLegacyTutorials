package com.thomasmylonas.spring_rest.helpers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * This is a Helper class which contains public static Java methods for general use.
 *
 * @author Thomas_Mylonas
 * @since 16-May-2022
 */
public abstract class HelperClass {

    /**
     * A String[] with the pattern: [delimiter, prefix, suffix], where:
     * delimiter: ", ",
     * prefix: "[",
     * suffix: "]"
     * This array is for simple uses like: [object1, object2, ..., object_n]
     */
    public static final String[] DISPLAY_INLINE_IN_CONSOLE = {", ", "[", "]"};

    /**
     * The method checks if a String is "null" or "empty"
     *
     * @param string The String we check if it is "null" or "empty"
     * @return A boolean which checks if a String is "null" or "empty"
     */
    public static boolean isStringNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * The method checks if an Array is "null" or "empty"
     *
     * @param array The Array we check if it is "null" or "empty"
     * @return A boolean which checks if an Array is "null" or "empty"
     */
    public static <T> boolean isArrayNullOrEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * The method checks if a List is "null" or "empty"
     *
     * @param list The List we check if it is "null" or "empty"
     * @return A boolean which checks if a List is "null" or "empty"
     */
    public static <T> boolean isListNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static Properties loadPropertiesFile(String propertiesPath) throws IOException {

        Properties properties = new Properties();
        if (isStringNullOrEmpty(propertiesPath)) {
            return properties;
        }
        properties.load(HelperClass.class.getClassLoader().getResourceAsStream(propertiesPath)); // IOException
        return properties;
    }

    public static String fetchPropertyValueFromProperties(String propertiesPath, String propertyName) throws IOException {

        if (isStringNullOrEmpty(propertiesPath) || isStringNullOrEmpty(propertyName)) {
            return "";
        }
        return loadPropertiesFile(propertiesPath).getProperty(propertyName); // IOException
    }

    public static String displayStringListAsString(String[] array, String[] delimPrefixSuffix) {
        return Arrays.stream(array).collect(Collectors.joining(delimPrefixSuffix[0], delimPrefixSuffix[1], delimPrefixSuffix[2]));
    }

    public static Date buildDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day); // cal.set(1972, Calendar.SEPTEMBER, 24);
        return cal.getTime();
    }
}
