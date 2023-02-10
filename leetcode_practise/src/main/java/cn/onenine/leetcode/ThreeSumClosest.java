package cn.onenine.leetcode;

import java.util.Arrays;

/**
 * Description：最接近的三数之和 leetcode 16题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/10 22:23
 */
public class ThreeSumClosest {

    /**
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int close =  nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 1; i++) {
            int c = nums[i];
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = c + nums[L] + nums[R];
                if (Math.abs(sum - target) < Math.abs(close - target)) {
                    close = sum;
                }
                if (sum < target) {
                    L++;
                } else if (sum > target){
                    R--;
                }else {
                    return target;
                }

            }
        }
        return close;
    }
}
