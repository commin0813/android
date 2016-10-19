package com.commin.pro.commin_pig_1000.util;

import java.util.List;

/**
 * Created by user on 2016-10-19.
 */
public class UtilCalculate {
    public static String Addition(String[] elements) {
        int result = 0;
        for (int i = 0; i < elements.length; i++) {
            int value = Integer.parseInt(elements[i]);
            result += value;
        }
        return String.valueOf(result);
    }
}
