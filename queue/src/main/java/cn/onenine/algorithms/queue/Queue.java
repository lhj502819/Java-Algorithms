package cn.onenine.algorithms.queue;

/**
 * Description：队列接口，定义一系列队列的行为
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:32
 */
public interface Queue<E> {

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

    /**
     * 出队，并删除
     */
    E poll();

    /**
     * 出队，不删除
     * @return
     */
    E peek();

}
