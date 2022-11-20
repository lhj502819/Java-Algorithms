package cn.onenine.algorithms.queue;

/**
 * Description：阻塞队列
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:32
 */
public interface BlockingQueue<E> extends Deque<E>{

    /**
     * 添加到队列尾
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 压入队列尾
     */
    boolean offer(E e);


}
