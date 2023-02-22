package cn.onenine.leetcode;

/**
 * Description：回文数
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/21 21:53
 */
public class LeetCode09_PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x >>> 31 == 1) {
            return false;
        }

        int t = x;

        int r = 0;
        while (t != 0) {
            r = r * 10 + t % 10;
            t /= 10;
        }

        return r == x;
    }

    public static void main(String[] args) {
        LeetCode09_PalindromeNumber leetCode09PalindromeNumber = new LeetCode09_PalindromeNumber();
        leetCode09PalindromeNumber.isPalindrome(121);
    }

    public char[] manacherString(int x) {
        String str = String.valueOf(x);
        String s = "#";
        for (int i = 0; i < str.length(); i++) {
            s += str.charAt(i) + "#";
        }
        return s.toCharArray();
    }

}
