package cn.onenine.algorithms.heap;

/**
 * Description：最大堆
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/28 21:06
 */
public class MaxHeap<E> extends Heap<Integer>{

    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return secondElement.compareTo(firstElement);
    }
}
