package com.commin.pro.commin_pig_1000.util;


/**
 * Created by user on 2016-10-19.
 */
public class UtilCalculate {
    public static String StringAddition(String[] elements) {
        int result = 0;
        for (int i = 0; i < elements.length; i++) {
            int value = Integer.parseInt(elements[i]);
            result += value;
        }
        return String.valueOf(result);
    }

    public static String StringAddition(String element,String element2) {
        int result = 0;
        result = Integer.parseInt(element)+Integer.parseInt(element2);
        return String.valueOf(result);
    }


    public static String StringSubtract(String result_value, String total_value) {
        int value = Integer.parseInt(result_value);
        int value_total = Integer.parseInt(total_value);
        return String.valueOf(value - value_total);
    }
}
