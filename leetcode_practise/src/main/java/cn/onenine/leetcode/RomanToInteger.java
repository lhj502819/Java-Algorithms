package cn.onenine.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：罗马数字转整数
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/6 21:34
 */
public class RomanToInteger {

    public int romanToInt(String s) {

        int i = 0;
        int r = 0;
        int j = s.length();
        //LVIII
        while (i <= j) {
            String ss = s.substring(i, j);
            if (getInt(ss) != null) {
                r += getInt(ss);
                i = j;
                j = s.length();
            } else {
                j--;
            }
        }
        return r;
    }

    public Integer getInt(String str) {
        switch (str) {
            case "M":
                return 1000;
            case "CM":
                return 900;
            case "D":
                return 500;
            case "CD":
                return 400;
            case "C":
                return 100;
            case "XC":
                return 90;
            case "L":
                return 50;
            case "XL":
                return 40;
            case "IX":
                return 9;
            case "V":
                return 5;
            case "IV":
                return 4;
            case "I":
                return 1;
            default:
                return null;
        }

    }

}


