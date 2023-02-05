package cn.onenine.javaAlgorithms.sort;

/**
 * Description：插入排序
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/5 9:51
 */
public class InsertionSort {

    /**
     * 5,9,3,7,11
     */
    public static int[] insertionSort(int[] array){
        if (array == null || array.length == 0) {
            return array;
        }

        int preIndex;
        int current ;
        for (int i = 1; i < array.length; i++) {
            preIndex = i - 1;
            current = array[i];
            while (preIndex >= 0 && array[preIndex] > current){
                //交换并将prIndex - 1
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;

        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = insertionSort(new int[]{5, 1, 4, 2, 8});
        for (int i : arr) {
            System.out.printf(i + ",");
        }
    }

}
