package cn.onenine.leetcode;

/**
 * Description：删除有序数组中的重复项
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/27 22:01
 */
public class LeetCode26_removeDuplicatesFromSortedArray {

    /**
     * 1,1,1,1,2,2,3,3
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        if (nums == null) {
            return 0;
        }

        if (nums.length < 2) {
            return nums.length;
        }
        int done = 0;
        /**
         * done表示目前的有效区域，如果done下标元素与遍历到的元素相等则done+1并且done+1的位置被当前遍历元素覆盖，有效区前进
         */
        for (int i = 1; i < nums.length; i++) {
            if (nums[done] != nums[i]) {
                nums[++done] = nums[i];
            }
        }

        return done + 1;
    }
}
