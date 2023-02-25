package cn.onenine.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/25 13:35
 */
public class LeetCode18_FourNumSum {




    public static void main(String[] args) {
        int[] ints = {1000000000,1000000000,1000000000,1000000000};

        System.out.println(Integer.MAX_VALUE);

        System.out.println(Integer.MIN_VALUE);


        LeetCode18_FourNumSum sum = new LeetCode18_FourNumSum();
        List<List<Integer>> lists = sum.fourSum(ints, -294967296);
    }


     /**
      * 需要考虑超出int范围的情况，因此内部都转换成long类型
      */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
//        if (nums[0] > target) {
//            return res;
//        }

        long[] numsL = new long[nums.length];
        for (int i = 0; i <nums.length; i++) {
            numsL[i] = nums[i];
        }
        for (int i = 0; i < numsL.length - 3; i++) {
            if (i == 0 || numsL[i] != numsL[i-1]){
                List<List<Integer>> lists = threeSum(numsL, i + 1, target - nums[i]);
                for (List<Integer> list : lists) {
                    list.add(0, nums[i]);
                    res.add(list);
                }
            }
        }

        return res;


    }

    public List<List<Integer>> threeSum(long[] nums, int begin, int target) {

        List<List<Integer>> res = new ArrayList<>();
        if (target > Integer.MAX_VALUE || target < Integer.MIN_VALUE){
            return res;
        }


        for (int i = begin; i < nums.length - 2; i++) {
            if (i == begin || nums[i] != nums[i - 1]) {
                List<List<Integer>> lists = twoNumSum(nums, i + 1, target -nums[i]);
                for (List<Integer> list : lists) {
                    list.add(0, Math.toIntExact(nums[i]));
                    res.add(list);
                }
            }
        }

        return res;

    }

    /**
     * x + y = target
     *
     * @param begin  起始位置
     * @param target 目标值
     */
    public List<List<Integer>> twoNumSum(long[] nums, int begin, long target) {

        List<List<Integer>> res = new ArrayList<>();

        if (target > Integer.MAX_VALUE || target < Integer.MIN_VALUE){
            return res;
        }

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
                    l.add(Math.toIntExact(nums[L]));
                    l.add(Math.toIntExact(nums[R]));
                    res.add(l);
                }
                L++;
            }
        }

        return res;


    }

}
