package cn.onenine.leetcode;


/**
 * Description：最长回文子串 leetcode 第5题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/1 21:04
 */
public class LongestPalindromicSubstring {

    /**
     * 暴力破解
     */
    public String longestPalindrome1(String s) {

        int len = s.length();
        if (len < 2) {
            return s;
        }

        //回文子串最大长度
        int maxLength = 1;
        int begin = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLength && valid(chars, i, j)) {
                    maxLength = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLength);
    }

    private boolean valid(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }


    /**
     * 中心扩散法
     * 如果回文串是奇数时，比如 "bab" ，它的中心点就只有一个 "a"，所以从 "a" 开始向两边扩散
     * 如果回文串是偶数时，比如 "baab"，它的中心点有两个 "aa"，所以从 "aa" 开始向两边扩散
     * 编写一个辅助函数来寻找回文串，当中心点确定了之后调用辅助函数，直接返回找到的回文串
     * 将每次找到的回文串与之前的做对比，谁长就留谁
     */
    public static String longestPalindrome3(String s) {

        String res = "";

        for (int i = 0; i < s.length(); i++) {
            //奇数，由当前点像两边扩散
            String s1 = test(s, i, i);
            //偶数，从+1向两边扩散
            String s2 = test(s, i, i + 1);

            res = s1.length() > res.length() ? s1 : res;
            res = s2.length() > res.length() ? s2 : res;
        }
        return res;
    }

    private static String test(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                //左右相等，继续扩大
                left--;
                right++;
            } else {
                //左右不相等，则跳出循环
                break;
            }
        }
        //因为在最后一次循环时，left--了，因此这里需要加回来，right已经++，因此不需要再++
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome3("abaababa"));
    }

}
