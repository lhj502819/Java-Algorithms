package cn.onenine.leetcode;

import java.util.Arrays;

/**
 * Description：最接近的三数之和
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/24 21:35
 */
public class LeetCode16_SumOfTheThreeClosestNumbers {

    /**
     * 与三数之和类似
     */
    public int threeSumClosest(int[] nums, int target) {

        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        //0,1,1,1
        //-100
        for (int i = 0; i < nums.length - 2; i++) {
            int L = i +1;
            int R = nums.length - 1;
            while (L < R){
                int sum = nums[L] + nums[R] + nums[i];
                //-100 - 2 = -102
                //-100 - 3 = -103
                if (Math.abs(target - sum) < Math.abs(target - res)){
                    res = sum;
                }
                if (sum < target){
                    L++;
                } else if (sum >target) {
                    R --;
                }else {
                    return res;
                }
            }
        }

        return res;

    }


}
