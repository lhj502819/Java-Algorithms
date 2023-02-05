package cn.onenine.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：数组中的第K个最大元素 leetcode 215题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/2 22:36
 */
public class KthLargestElementInAnArray {

    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 输入: [3,2,1,5,6,4], k = 2
     * 输出: 5
     *
     * 通过快速排序的分而治之的方式
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return 0;
        }

        //目标元素
        int target = nums.length - k;


        return 0;
    }


}
