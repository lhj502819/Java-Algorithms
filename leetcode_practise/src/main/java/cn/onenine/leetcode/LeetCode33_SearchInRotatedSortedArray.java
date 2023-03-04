package cn.onenine.leetcode;

/**
 * Description：搜索旋转排序数组
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/3/4 13:56
 */
public class LeetCode33_SearchInRotatedSortedArray {


    /**
     * 使用二分查找方法，但需要有几个注意的点
     * 整个数组部分有序，需要逐步找到有序的部分进行二分查找
     * <p>
     * 1、整体有序
     * 2、部分有序
     */
    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }


        int l = 0;
        int mid;
        int r = nums.length - 1;
        while (l <= r) {
            mid = (l + r) / 2;
            //如果当前中心位置元素就等于target，直接返回
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                //有序的
                if (nums[0] <= target && nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                //如果左半部分不是有序的，那右半部分一定是有序的，那就先判断target在不在右半部分
                //如果在右半部分，则l直接变为mid+1
                //如果不在右半部分，则r直接缩小范围，变为mid-1

                //mid = 0  3
                //l=0
                //r=1
                if (target > nums[mid] && target <= nums[r]) {
                    //如果在有序的部分那直接将l变为mid+1
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1;

    }


    public int search2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode33_SearchInRotatedSortedArray leetCode33SearchInRotatedSortedArray = new LeetCode33_SearchInRotatedSortedArray();
        System.out.println(leetCode33SearchInRotatedSortedArray.search2(new int[]{3, 1}, 1));
    }

}
