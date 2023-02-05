package cn.onenine.javaAlgorithms.sort;

import java.util.Arrays;

/**
 * Description：归并排序 ，https://www.bilibili.com/video/BV1Pt4y197VZ/?spm_id_from=333.337.search-card.all.click&vd_source=77f5aa3be4c6c3fc2d3d0595fefc7bfc
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/5 11:14
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 6, 4};
        mergeSort(arr);
        System.out.printf(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        int tempArr[] = new int[arr.length];
        msor(arr, tempArr, 0, arr.length - 1);

    }

    //归并排序
    static void msor(int[] arr, int[] tempArr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            msor(arr, tempArr, left, mid);
            msor(arr, tempArr, mid + 1, right);
            //合并已经排序的部分
            merge(arr, tempArr, left, mid, right);

        }
    }

    /**
     * 合并
     */
    private static void merge(int[] arr, int[] tempArr, int left, int mid, int right) {

        //标记左半区第一个未排序的元素
        int l_pos = left;
        //标记右半区第一个未排序的元素
        int r_pos = mid + 1;
        //临时数组下标
        int pos = left;

        //合并
        while (l_pos <= mid && r_pos <= right) {
            if (arr[l_pos] > arr[r_pos]) {
                tempArr[pos++] = arr[r_pos++];
            } else {
                tempArr[pos++] = arr[l_pos++];
            }
        }

        //左半区有剩余，直接放到临时数组尾部
        while (l_pos <= mid){
            tempArr[pos++] = arr[l_pos++];
        }

        //左半区有剩余，直接放到临时数组尾部
        while (r_pos <= right){
            tempArr[pos++] = arr[r_pos++];
        }

        //把临时数组合并后的数据复制回原来的数组
        while (left <= right){
            arr[left] = tempArr[left];
            left++;
        }

    }


}
