package cn.onenine.algorithms.queue;

/**
 * Description：双端队列
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:39
 */
public interface Deque<E> extends Queue<E>{

    /**
     * 添加到队列头
     */
    boolean addFirst(E e);

    /**
     * 添加到队列尾
     */
    boolean addLast(E e);

}
