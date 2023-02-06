package cn.onenine.leetcode;

/**
 * Description：整数转罗马数字 leetcode 12题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/6 21:09
 */
public class IntegerToRoman {

    public String intToRoman(int num) {

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder symbol = new StringBuilder();
        for (int i = 0; i < values.length; ) {
            if (num >= values[i]) {
                symbol.append(symbols[i]);
                num -= values[i];
                continue;
            }
            i++;
            if (num == 0){
                break;
            }
        }
        return symbol.toString();
    }


}
