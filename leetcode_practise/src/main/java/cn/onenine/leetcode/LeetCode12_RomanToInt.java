package cn.onenine.leetcode;

/**
 * Description：罗马数转int
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/22 21:17
 */
public class LeetCode12_RomanToInt {

    /**
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public int romanToInt(String s) {

        int[] nums = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    nums[i] = 1;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'M':
                    nums[i] = 1000;
                    break;
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length -1 && nums[i] < nums[i + 1]) {
                result += -nums[i];
            } else {
                result += nums[i];
            }
        }

        return result;


    }

}
