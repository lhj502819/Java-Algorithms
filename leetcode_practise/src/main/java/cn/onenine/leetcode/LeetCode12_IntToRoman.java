package cn.onenine.leetcode;

/**
 * Description：数字转罗马
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/22 20:59
 */
public class LeetCode12_IntToRoman {

    public String intToRoman(int num) {

        String[][] c = new String[][]{
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };

        return c[3][num / 1000 % 10] +
                c[2][num / 100 % 10] +
                c[1][num / 10 % 10] +
                c[0][num % 10];


    }

}
