package cn.onenine.javaAlgorithms.sort;

/**
 * Description：冒泡排序
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/5 9:15
 */
public class BubbleSort {


    /**
     * 5,1,4,2,8
     */
    public static int[] bubbleSort(int[] array) {
        int size = array.length;
        // loop to access each array element
        for (int i = 0; i < size - 1; i++) { //控制循环次数
            //这里的j < size - 1 - i 是为了减少一次比较
            //不 -i 也可以
            //每循环完一次，其实是可以在下次循环中少比较一次的，因为后i + 1个元素已经是有序的了
            for (int j = 0; j < size - 1 - i; j++)
                // compare two adjacent elements
                // change > to < to sort in descending order
                if (array[j] > array[j + 1]) {
                    // swapping occurs if elements
                    // are not in the intended order
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = bubbleSort(new int[]{5, 1, 4, 2, 8});
        for (int i : arr) {
            System.out.printf(i + ",");
        }

    }


}
