package cn.onenine.javaAlgorithms.sort;

/**
 * Description：选择排序
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/5 9:41
 */
public class SelectionSort {

    public static int[] selectionSort(int[] array) {

        if (array == null || array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                //找到一个从当前位置开始，最小的元素，与min位置交换
                //如果i=0，那就是找到最小
                //如果i=1，那就是找到第二小的
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = selectionSort(new int[]{5, 1, 4, 2, 8});
        for (int i : arr) {
            System.out.printf(i + ",");
        }
    }

}
