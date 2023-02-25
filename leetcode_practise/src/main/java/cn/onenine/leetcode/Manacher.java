package cn.onenine.leetcode;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/19 15:11
 */
public class Manacher {
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] strC = manacherString(s);
        //存储每个元素的最长回文半径
        int[] arr = new int[strC.length];
        //当前最长回文右边界 +1
        int R = -1;
        //当前最长回文中心
        int C = -1;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < strC.length; i++) {
            //确定当前i为中心的最小回文半径是多大，然后再继续往外扩
            //当R<=i 最小回文半径至少是它本身，也就是1
            //当R>i时，分三种情况
            //  ①: i'的L和R在 当前L、R范围内，则i的最大回文半径是i'的回文半径，小于R-i
            //  ②: i'的L在 超出当前的L，则i的最大回文半径是R-i
            //  ③: i'的L与当前的L压线（相等），则i的回文半径，至少是R-i
            // 综上三种情况，则回文半径只有两种情况，i'的回文半径 和 R-i取最小值，最小值则是我们想要的结果
            //针对第①、②种情况， 都不需要再继续向两侧扩，因为已经能确定最大回文半径了
            //只有针对第③种情况，才不确定是否已经是最大回文半径，需要继续扩，因此在下方需要进行判断
            arr[i] = R > i ? Math.min(arr[C * 2 - i], R - i) : 1;

            //确定完最小回文，进行两侧扩容比对
            //如果以i为中心，加上和减去最小回文半径不超过当前的字符串的两端，则继续扩
            while (i + arr[i] < strC.length && i - arr[i] > -1) {
                if (strC[i + arr[i]] == strC[i - arr[i]]) {
                    arr[i] = arr[i] + 1;
                } else {
                    break;
                }
            }

            if (i + arr[i] > R) {
                R = i + arr[i];
                C = i;
            }
            max = Math.max(max, arr[i]);
        }

        //#a#b#b#a#
        //C = 4
//        return (max * 2 - 1) / 2;
        //(max * 2 - 1) / 2 等价于 max - 1
        return max - 1;
    }

    public static char[] manacherString(String str) {
        String result = "#";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i) + "#";
        }
        return result.toCharArray();
    }
}
