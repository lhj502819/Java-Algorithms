package cn.onenine.javaAlgorithms.sort;

import java.util.Arrays;

/**
 * Description：希尔排序
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/5 10:18
 */
public class ShellSort {

    public static int[] shellSort(int[] arr) {

        //遍历所有的步长
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            //遍历所有的元素
            for (int i = gap; i < arr.length; i++) {
                //遍历本组中所有元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //打印每次排序后的结果
            System.out.println(Arrays.toString(arr));
        }

        return arr;
    }


    public static void main(String[] args) {
        int[] arr = shellSort(new int[]{5, 1, 4, 2, 8});
        for (int i : arr) {
            System.out.printf(i + ",");
        }
    }

}
