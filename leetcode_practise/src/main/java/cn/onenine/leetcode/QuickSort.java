package cn.onenine.leetcode;

/**
 * Description：快速排序算法
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/2 23:27
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] ints = {4, 2, 14, 5, 2};
        QuickSort quickSort = new QuickSort();
        int[] ints1 = quickSort.sortArray(ints);
        for (int i : ints1) {
            System.out.printf(i + "");
        }
    }

    public int[] sortArray(int[] array) {

        quickSort(array, 0, array.length - 1);
        return array;
    }

    public void quickSort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivotIndex = partition(nums, left, right);
        //左分区排序
        quickSort(nums, left, pivotIndex - 1);
        //右分区排序
        quickSort(nums, pivotIndex + 1, right);
    }

    /**
     * 分区排序
     *
     * @param left  左边界
     * @param right 有边界
     */
    private int partition(int[] nums, int left, int right) {
        //获取到左边界的值
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i < right; i++) {
            //当前元素小于分区元素，则把当前元素与右分区的最左边
            if (nums[i] <= pivot) {
                j++;
                swap(nums, i, j);
            }
        }

        //最终把分区元素放到左分区的最右边，也就是当前元素正确的位置
        swap(nums, left, j);
        //分区元素在整个数组中按照升序排序的位置
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int old = nums[i];
        nums[i] = nums[j];
        nums[j] = old;
    }

}
