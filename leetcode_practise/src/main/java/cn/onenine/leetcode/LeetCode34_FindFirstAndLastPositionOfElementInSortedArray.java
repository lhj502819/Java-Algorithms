package cn.onenine.leetcode;

/**
 * Description：在排序数组中查找元素的第一个和最后一个位置
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/3/4 14:37
 */
public class LeetCode34_FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0){
            return new int[]{-1, -1};
        }

        if (nums.length == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target <= nums[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (nums[r] != target) {
            return new int[]{-1, -1};
        }

        int resultL = r;
        l = 0;
        r = nums.length - 1;
        while (l < r) {
            int mid = (r + l + 1) / 2;
            if (target >= nums[mid]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return new int[]{resultL,r};
    }

    public static void main(String[] args) {
        LeetCode34_FindFirstAndLastPositionOfElementInSortedArray leetCode34FindFirstAndLastPositionOfElementInSortedArray = new LeetCode34_FindFirstAndLastPositionOfElementInSortedArray();
        leetCode34FindFirstAndLastPositionOfElementInSortedArray.searchRange(new int[]{5,7,7,8,8,10},8);
    }

}
