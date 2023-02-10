package cn.onenine.leetcode;

/**
 * Description：最长公共前缀 leetcode 14题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/8 22:28
 */
public class LongestCommonPrefix {

    /**
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String maxCommonPrefix = strs[0];

        /**
         * 依次比较，每次获取最短的
         */
        for (int i = 1; i < strs.length; i++) {
            maxCommonPrefix = getLongestCommonPrefix(maxCommonPrefix, strs[i]);
            if (maxCommonPrefix.length() == 0) {
                break;
            }
        }
        return maxCommonPrefix;
    }

    private String getLongestCommonPrefix(String str1, String str2) {
        int index = 0;
        int length = Math.min(str2.length(), str1.length());

        while (index < length && str2.charAt(index) == str1.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

}
