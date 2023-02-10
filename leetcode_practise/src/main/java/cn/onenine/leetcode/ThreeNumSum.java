package cn.onenine.leetcode;

import java.util.*;

/**
 * Description：三数之和 leetcode 13题
 *
 * 题解：https://leetcode.cn/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/10 21:32
 */
public class ThreeNumSum {

    /**
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        //-4,-1,-1,0,1,2
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = c + nums[R] + nums[L];
                if (sum > 0) {
                    R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    //-4,-1,-1,0,1,2
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));

                    /**
                     * 当 sum == 0 时，nums[L] == nums[L+1] 则会导致结果重复，应该跳过，L++L++
                     * 当 sum == 0 时，nums[R] == nums[R-1] 则会导致结果重复，应该跳过，R--R−−
                     */
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    L++;
                    R--;
                }

            }

        }
        return res;

    }

}
