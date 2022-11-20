package cn.onenine.algorithms.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:41
 */
public class DelayQueue<E extends Delayed> implements BlockingQueue<E>{

    private final transient ReentrantLock lock = new ReentrantLock();

    private final PriorityQueue<E> q = new PriorityQueue<E>();

    private final Condition available = lock.newCondition();


    public boolean add(E e) {
        return q.add(e);
    }

    public boolean offer(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            q.offer(e);
            if (q.peek() == e) {
                available.signal();
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E first = q.peek();
            if (first == null || first.getDelay(NANOSECONDS) > 0) {
                return null;
            } else {
                return q.poll();
            }
        } finally {
            lock.unlock();
        }
    }

    public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.peek();
        }finally {
            lock.unlock();
        }
    }

    public boolean addFirst(E e) {
        return false;
    }

    public boolean addLast(E e) {
        return false;
    }
}
