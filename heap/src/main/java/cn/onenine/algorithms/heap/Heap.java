package cn.onenine.algorithms.heap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Description：堆实现
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/28 20:41
 */
public class Heap<E> implements IHeap<E> {

    private static final Logger logger = LoggerFactory.getLogger(Heap.class);

    private final int DEFAULT_INITIAL_CAPACITY = 10;

    transient Object[] queue;

    private int size = 0;

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
        logger.info("【入队】完成 Idx：{} Val：{} \r\n当前队列：{} \r\n", k, com.alibaba.fastjson.JSON.toJSONString(key), JSONObject.toJSONString(queue));
    }

    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        int i = size;
        if (i >= queue.length) {
            grow(i + 1);
        }
        size = i + 1;
        if (i == 0) {
            queue[0] = e;
        } else {
            siftUp(i, e);
        }
        return true;
    }

    private void siftUp(int k, E x) {
        siftUpComparable(k, x);
    }

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
        int s = --size;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0) {
            siftDown(0, x);
        }
        return result;
    }

    private void siftDown(int k, E x) {
        siftDownComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k, E x) {
        // 先找出中间件节点
        int half = size >>> 1;
        while (k < half) {
            // 找到左子节点和右子节点，两个节点进行比较，找出最大的值
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            // 左子节点与右子节点比较，取最小的节点
            if (right < size && compareTo((E) c, (E) queue[right]) > 0) {
                logger.info("【出队】左右子节点比对，获取最小值。left：{} right：{}", JSON.toJSONString(c), JSON.toJSONString(queue[right]));
                c = queue[child = right];
            }
            // 目标值与c比较，当目标值小于c值，退出循环。说明此时目标值所在位置适合，迁移完成。
            if (compareTo(x, (E) c) <= 0) {
                break;
            }
            // 目标值小于c值，位置替换，继续比较
            logger.info("【出队】替换过程，节点的值比对。上节点：{} 下节点：{} 位置替换", JSON.toJSONString(queue[k]), JSON.toJSONString(c));
            queue[k] = c;
            k = child;
        }
        // 把目标值放到对应位置
        logger.info("【出队】替换结果，最终更换位置。Idx：{} Val：{}", k, JSON.toJSONString(x));
        queue[k] = x;
    }

    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    public int compareTo(E firstElement, E secondElement) {
        throw new RuntimeException("未实现 compareTo 方法");
    }
}
