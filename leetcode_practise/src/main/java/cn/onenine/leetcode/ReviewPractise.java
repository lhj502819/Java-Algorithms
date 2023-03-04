package cn.onenine.leetcode;


import java.util.*;

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


    /**
     * 最多盛水面积
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            maxArea = Math.max((j - i) * Math.min(height[i], height[j]), maxArea);

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }


    /**
     * 数字转罗马数
     */
    public String intToRoman(int num) {

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
        String[][] strings = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };
        String result = "";
        //3566
        result = strings[3][(num / 1000) % 10]
                + strings[2][(num / 100) % 10]
                + strings[1][(num / 10) % 10]
                + strings[0][num % 10];


        return result;

    }


    /**
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * IVI
     */
    public int romanToInt(String s) {

        int result = 0;


        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1) {
                result += getInt(s.charAt(i));
            } else {
                int n1 = getInt(s.charAt(i));
                int n2 = getInt(s.charAt(i + 1));
                result += n1 >= n2 ? n1 : -n1;
            }
        }

        return result;


    }

    public int getInt(char s) {
        switch (s) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }

    /**
     * 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String maxPrefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            if (maxPrefix.length() == 0)
                return "";

            int min = Math.min(strs[i].length(), maxPrefix.length());
            maxPrefix = maxPrefix.substring(0, min);
            strs[i] = strs[i].substring(0, min);

            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) != maxPrefix.charAt(j)) {
                    maxPrefix = maxPrefix.substring(0, j);
                    break;
                }
            }
        }

        return maxPrefix;
    }


    /**
     * 三数之和
     * 思路：
     * 先思考二元组怎么求，给一个目标值，从数组中找出两个元素和为目标值的二元组
     * 从两侧往中间推，假设左边下标为L，右边下标为R
     * 当nums[L] + nums[R] < target时，L+1
     * 当nums[L] + nums[R] > target时，R-1
     * 当nums[L] + nums[R] == target时，判断nums[L]是否等于nums[L-1]，如果相等则跳过，因为[L-1]的结果已经遍历过
     * 直到L==R停止
     * <p>
     * 求三元组的话其实就是从0遍历每个元素，nums[i] + nums[x] + nums[y] = target，那target - nums[i] =  nums[x] + nums[y]
     * 其实还是在求二元组，只不过二元组的target是 三元组target-nums[i]
     * 在这个题中，三元组的target为0
     */
    public List<List<Integer>> threeSum(int[] nums) {


        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        if (nums[0] > 0) {
            return res;
        }


        for (int i = 0; i < nums.length - 2; i++) {

            if (i == 0 || nums[i] != nums[i - 1]) {
                List<List<Integer>> lists = twoSum(nums, i + 1, -nums[i]);
                for (List<Integer> list : lists) {
                    list.add(0, nums[i]);
                    res.add(list);
                }
            }
        }

        return res;

    }

    public List<List<Integer>> twoNumSum(int[] nums, int begin, int target) {

        List<List<Integer>> res = new ArrayList<>();

        int L = begin;
        int R = nums.length - 1;
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                //如果L是起始位置，或者L不等于前一位
                if (L == begin || nums[L] != nums[L - 1]) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[L]);
                    l.add(nums[R]);
                    res.add(l);
                }
                L++;
            }
        }
        return res;

    }


    public List<List<Integer>> twoSum(int[] nums, int begin, int target) {

        List<List<Integer>> res = new ArrayList<>();

        int L = begin;
        int R = nums.length - 1;

        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                if (L == begin || nums[L] != nums[L - 1]) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[L]);
                    l.add(nums[R]);
                    res.add(l);
                    L++;
                }
            }
        }

        return res;
    }


    public String reviewManacher(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }

        char[] strC = manacherStr(s);

        //回文中心坐标
        int C = 0;
        //回文右边界
        int R = -1;
        //回文左边界 = C * 2 - i
        //保存每一个元素的回文半径
        int maxLength = 0;
        int maxMid = 0;
        int[] arr = new int[s.length()];

        for (int i = 0; i < strC.length; i++) {
            //确定当前元素为中心的最小回文半径
            arr[i] = R > i ? Math.min(arr[C * 2 - i], R - i) : 1;

            while (i - arr[i] > -1 && i + arr[i] < strC.length) {
                if (strC[i + arr[i]] == strC[i - arr[i]]) {
                    arr[i] += 1;
                } else {
                    break;
                }
            }

            if (i + arr[i] > R) {
                R = i + arr[i];
                C = i;
            }
            if (maxLength < arr[i]) {
                maxMid = i;
                maxLength = arr[i];
            }
        }

        return new String(strC).substring(maxMid - arr[maxMid], maxMid + arr[maxMid] - 1);

    }

    static char[][] phone = new char[][]{
            {},
            {},
            {'a', 'b', 'c'},//2
            {'d', 'e', 'f'},//3
            {'g', 'h', 'i'},//4
            {'j', 'k', 'l'},//5
            {'m', 'n', 'o'},//6
            {'p', 'q', 'r', 's'},//7
            {'t', 'u', 'v'},//8
            {'w', 'x', 'y', 'z'},//9
    };

    /**
     * leetcode17题，电话号码的字母组合
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }


        char[] chars = digits.toCharArray();
        char[] path = new char[digits.length()];
        process(chars, 0, path, ans);

        return ans;
    }

    //245
    //2 {a,b,c}
    //4 {g,h,i}
    public void process(char[] digits, int index, char[] path, List<String> ans) {
        if (digits.length == index) {
            ans.add(String.valueOf(path));
        } else {
            //获取到当前数字对应的所有字符
            char[] c = phone[Integer.parseInt(String.valueOf(digits[index]))];
            //遍历所有字符
            for (char c1 : c) {
                path[index] = c1;
                //每次递归只是传入了index + 1，index没有变
                process(digits, index + 1, path, ans);
            }
        }
    }


    /**
     * Leetcode第18题，删除倒数第N个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) {
            return head;
        }
        ListNode r = head;
        ListNode c = head;
        ListNode prev = null;
        for (int i = 0; i < n; i++) {
            if (c == null) {
                return head;
            }
            c = c.next;
        }
        while (c != null) {
            c = c.next;
            prev = r;
            r = r.next;
        }
        if (prev == null) {
            return head.next;
        }
        prev.next = r.next;

        return head;


    }

    /**
     * leetcode21题，合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }

    }


    /**
     * 括号生成
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();
        process("", n, n, ans);
        return ans;
    }

    public void process(String s, int left, int right, List<String> ans) {

        if (left == 0 && right == 0) {
            ans.add(s);
        } else if (left > 0) {
            process(s + "(", left - 1, right, ans);
        } else if (right > left) {
            process(s + ")", left, right - 1, ans);
        }

    }


    public static class ListNodeComparable implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }


    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }


        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparable());

        Arrays.stream(lists).filter(Objects::nonNull).forEach(heap::offer);

        if (heap.isEmpty()) {
            return null;
        }

        ListNode head = heap.poll();
        ListNode prev = head;
        if (head.next != null) {
            heap.offer(prev.next);
        }

        while (heap.poll() != null) {
            ListNode node = heap.poll();
            prev.next = node;
            prev = node;
            if (node.next != null) {
                heap.offer(node.next);
            }
        }

        return head;

    }

    /**
     * 删除有序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {

        if (nums == null || nums.length == 0){
            return 0;
        }

        if (nums.length < 2){
            return nums.length;
        }

        //当前移动到的位置
        int done = 0;
        for (int i = 1; i < nums.length; i++) {
            if ( nums[done] != nums[i]) {
                nums[++done] = nums[i];
            }
        }
        return done + 1;

    }


    public int removeElement(int[] nums, int val) {

        if (nums == null || nums.length == 0){
            return 0;
        }

        int done = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val ){
                nums[++done] = nums[i];
            }
        }
        return done+1;
    }

    /**
     * 找出字符串中第一个匹配项的下标
     */
    public int strStr(String haystack, String needle) {

        if (haystack.length() < needle.length()){
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0) &&
                    (haystack.length() - i >= needle.length())){

                if (haystack.substring(i,i+needle.length()).equals(needle)){
                    return i;
                }
            }
        }

        return -1;
    }

}

