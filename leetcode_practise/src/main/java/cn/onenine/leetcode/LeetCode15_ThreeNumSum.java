package cn.onenine.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description：三数之和
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/23 22:13
 */
public class LeetCode15_ThreeNumSum {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        if (nums[0] > 0){
            return res;
        }

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i-1]){
                List<List<Integer>> lists = twoNumSum(nums, i + 1, -nums[i]);
                for (List<Integer> list : lists) {
                    list.add(0,nums[i]);
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

}
