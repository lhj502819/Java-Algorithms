package cn.onenine.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和 leetcode 第1题
 */
public class LeetCode01_TwoNumSum {

    /**
     * 暴力破解方式
     */
    public int[] twoSum1(int[] nums, int target) {

        if (nums == null || nums.length > 0 || target <= 0) {
            return new int[0];
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 优解
     */
    public int[] twoSum2(int[] nums, int target) {

        if (nums == null || nums.length > 0 ) {
            return new int[0];
        }

        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }


        return new int[0];
    }

    public static void main(String[] args) {
        LeetCode01_TwoNumSum twoNumSum = new LeetCode01_TwoNumSum();
        int[] ints = twoNumSum.twoSum1(new int[]{2, 7, 11, 15}, 9);
        for (int o : ints) {
            System.out.println(o + ",");
        }
    }
}