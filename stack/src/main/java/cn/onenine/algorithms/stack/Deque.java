package cn.onenine.algorithms.stack;

/**
 * Description：双端队列
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:39
 */
public interface Deque<E>{

    void push(E e);

    E pop();

    boolean isEmpty();

}
