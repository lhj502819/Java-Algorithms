package cn.onenine.leetcode;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/27 22:26
 */
public class LeetCode27_RemoveElement {

    public int removeElement(int[] nums, int val) {

        if (nums == null){
            return 0;
        }

        //332232
        int done = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] !=val){
                nums[++done] = nums[i];
            }
        }

        return done + 1;

    }
}
