package cn.onenine.leetcode;

/**
 * Description：最长公共前缀
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/22 21:37
 */
public class LeetCode14_MaxPrefix {

    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        String maxPrefix = strs[0];


        //ab a
        for (int i = 1; i < strs.length; i++) {
            if (maxPrefix.length() == 0)
                return "";

            int minLength = Math.min(maxPrefix.length(),strs[i].length());
            maxPrefix = maxPrefix.substring(0,minLength);

            strs[i] = strs[i].substring(0,minLength);
            for (int j = 0; j < strs[i].length(); j++) {
                if ((maxPrefix.charAt(j) != strs[i].charAt(j))) {
                    maxPrefix = maxPrefix.substring(0, j);
                    break;
                }
            }
        }

        return maxPrefix;
    }

}
