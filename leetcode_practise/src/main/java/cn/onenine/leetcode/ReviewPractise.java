package cn.onenine.leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：题目回顾
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/19 9:37
 */
public class ReviewPractise {

    public int[] twoSum(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int nd = target - nums[i];
            if (map.containsKey(nd)) {
                return new int[]{map.get(nd), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        //进位
        int carry = 0;
        ListNode head = null;
        ListNode prev = null;
        ListNode c1 = l1;
        ListNode c2 = l2;

        while (c1 != null || c2 != null) {
            int v1 = c1 != null ? c1.val : 0;
            int v2 = c2 != null ? c2.val : 0;
            //求和
            int sum = v1 + v2 + carry;
            //进位，对10取余
            carry = sum / 10;
            //当前值
            int current = sum % 10;
            ListNode nowNode = new ListNode(current);
            if (head == null) {
                head = nowNode;
            } else {
                prev.next = nowNode;
            }
            prev = nowNode;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }

        if (carry != 0) {
            prev.next = new ListNode(carry);
        }

        return head;
    }


    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int[] arr = new int[256];
        Arrays.fill(arr, -1);
        int maxLength = 0;
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            prev = Math.max(arr[s.charAt(i)], prev);
            maxLength = Math.max(maxLength, i - prev);
            arr[s.charAt(i)] = i;
        }

        return maxLength;
    }


    /**
     * N字形变化
     */
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return s;
        }

        if (numRows == 1) {
            return s;
        }

        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }

        //PAYPALISHIRING
        //0  1  2
        //P  A  Y
        int flag = 1;
        int lineNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (lineNum == numRows - 1) {
                flag = -1;
            }

            if (lineNum == 0) {
                flag = 1;
            }

            sbs[lineNum].append(s.charAt(i));

            lineNum += flag;
        }

        StringBuilder res = new StringBuilder();

        for (StringBuilder sb : sbs) {
            res.append(sb);
        }

        return res.toString();
    }

    public int reverse(int x) {
        boolean neg = x >>> 31 == 1;
        x = neg ? x : -x;

        int m = Integer.MIN_VALUE / 10;
        int o = Integer.MIN_VALUE % 10;

        int res = 0;
        while (x != 0) {
            if (res < m || res == m && x % 10 < o) {
                return 0;
            }
            //654
            res = res * 10 + x % 10;

            x /= 10;
        }

        return neg ? res : Math.abs(res);

    }

    public int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = manacherStr(s);

        //中心点
        int C = -1;
        //回文串右边界 + 1
        int R = -1;
        //存放每个元素的回文串半径
        int[] arr = new int[str.length];
        int maxLength = 0;

        //最大回文子串的中心坐标
        int mid = 0;
        for (int i = 0; i < str.length; i++) {

            arr[i] = R > i ? Math.min(arr[C * 2 - i], R - i) : 1;

            while (i - arr[i] > -1 && i + arr[i] < str.length) {
                if (str[i - arr[i]] == str[i + arr[i]]) {
                    arr[i] = arr[i] + 1;
                } else {
                    break;
                }
            }

            if (i + arr[i] > R) {
                R = i + arr[i];
                C = i;
            }
            if (maxLength < arr[i]) {
                mid = i;
                maxLength = arr[i];
            }
        }
        //a#b#c#d#d#c#
        return maxLength - 1;

    }

    public char[] manacherStr(String s) {
        String res = "#";
        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i) + "#";
        }
        return res.toCharArray();
    }


}
