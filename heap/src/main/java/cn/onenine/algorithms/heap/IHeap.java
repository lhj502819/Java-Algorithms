package cn.onenine.algorithms.heap;

/**
 * Description：堆
 *
 * Java泛型中的标记符含义：
 * E - Element （元素,在集合中使用）
 * T - Type （Java类）
 * K - Key （键）
 * V - Value （值）
 * N - Number（数值类型）
 * ? - 表示不确定的Java类型
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/28 20:45
 */
public interface IHeap<E> {

    boolean add(E e);

    boolean offer(E e);

    E poll();

    E peek();
}
