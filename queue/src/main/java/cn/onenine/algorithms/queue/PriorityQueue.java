package cn.onenine.algorithms.queue;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Description：优先级队列
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:36
 */
public class PriorityQueue<E> implements Queue<E> {

    private Logger logger = LoggerFactory.getLogger(PriorityQueue.class);

    private final int DEFAULT_INITIAL_CAPACITY = 10;

    transient Object[] queue;

    private int size = 0;

    public PriorityQueue() {
        queue = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        //当前队列大小
        int i = size;
        //先判断是否需要扩容发
        if (i >= queue.length) {
            //扩容
            grow(i + 1);
        }
        //队列大小+1
        size = i + 1;
        if (i == 0) {
            //如果是第一个元素，则直接放到0号位置
            queue[0] = e;
        } else {
            //否则按照二叉堆规则插入
            siftUp(i, e);
        }
        return true;
    }

    private void siftUp(int k, E x) {
        siftUpComparable(k, x);
    }

    /**
     * 二叉堆在存放元素时，会遵循它的特点，在存放过程中，通过队尾元素向上对比迁移，上浮操作
     */
    @SuppressWarnings("uncheck")
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        logger.info("【入队】元素：{} 当前队列：{}", com.alibaba.fastjson.JSON.toJSONString(key), com.alibaba.fastjson.JSON.toJSONString(queue));
        while (k > 0) {
            // 获取父节点Idx，相当于除以2
            int parent = (k - 1) >>> 1;
            logger.info("【入队】寻找当前节点的父节点位置。k：{} parent：{}", k, parent);
            Object e = queue[parent];
            // 如果当前位置元素，大于父节点元素，则退出循环
            if (key.compareTo((E) e) >= 0) {
                logger.info("【入队】值比对，父节点：{} 目标节点：{}", com.alibaba.fastjson.JSON.toJSONString(e), com.alibaba.fastjson.JSON.toJSONString(key));
                break;
            }
            // 相反父节点位置大于当前位置元素，则进行替换
            logger.info("【入队】替换过程，父子节点位置替换，继续循环。父节点值：{} 存放到位置：{}", com.alibaba.fastjson.JSON.toJSONString(e), k);
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
        logger.info("【入队】完成 Idx：{} Val：{} \r\n当前队列：{} \r\n", k, com.alibaba.fastjson.JSON.toJSONString(key), JSON.toJSONString(queue));
    }

    /**
     * 扩容
     */
    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - (Integer.MAX_VALUE - 8) > 0)
            newCapacity = (minCapacity > Integer.MAX_VALUE - 8) ?
                    Integer.MAX_VALUE :
                    Integer.MAX_VALUE - 8;
        queue = Arrays.copyOf(queue, newCapacity);
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        //队列大小 - 1
        int s = --size;
        //拿到队列头的元素，这个元素是最小的
        E result = (E) queue[0];
        //获取队尾的元素(最大的)
        E x = (E) queue[s];
        //将队列的尾元素设置为null
        queue[s] = null;
        if (s != 0) {
            //如果队列还有元素
            siftDown(0, x);
        }
        return result;
    }

    private void siftDown(int k, E x) {
        siftDownComparable(k, x);
    }

    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        // 先找出中间件节点
        int half = size >>> 1;
        while (k < half) {
            // 找到左子节点和右子节点，两个节点进行比较，找出最大的值
            int child = (k << 1) + 1;
            Object left = queue[child];
            int right = child + 1;
            // 左子节点与右子节点比较，取最小的节点
            if (right < size && ((Comparable<? super E>) left).compareTo((E) queue[right]) > 0) {
                logger.info("【出队】左右子节点比对，获取最小值。left：{} right：{}", com.alibaba.fastjson.JSON.toJSONString(left), com.alibaba.fastjson.JSON.toJSONString(queue[right]));
                left = queue[child = right];
            }
            // 目标值与left比较，当目标值小于left值，退出循环。说明此时目标值所在位置适合，迁移完成。
            if (key.compareTo((E) left) <= 0) {
                break;
            }
            // 目标值小于left值，位置替换，继续比较
            logger.info("【出队】替换过程，节点的值比对。上节点：{} 下节点：{} 位置替换", com.alibaba.fastjson.JSON.toJSONString(queue[k]), com.alibaba.fastjson.JSON.toJSONString(left));
            queue[k] = left;
            k = child;
        }
        // 把目标值放到对应位置
        logger.info("【出队】替换结果，最终更换位置。Idx：{} Val：{}", k, com.alibaba.fastjson.JSON.toJSONString(key));
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }
}
