package cn.onenine.algorithms.queue.test;


import cn.onenine.algorithms.queue.DelayQueue;
import cn.onenine.algorithms.queue.Delayed;
import cn.onenine.algorithms.queue.Queue;

import java.util.concurrent.TimeUnit;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 11:42
 */
public class AppTest {

    public static void main(String[] args) throws InterruptedException {
        Queue<Job> queue = new DelayQueue<Job>();
        queue.add(new Job("1号", 1000L));
        queue.add(new Job("3号", 3000L));
        queue.add(new Job("5号", 5000L));
        queue.add(new Job("11号", 11000L));
        queue.add(new Job("4号", 4000L));
        queue.add(new Job("6号", 6000L));
        queue.add(new Job("7号", 7000L));
        queue.add(new Job("12号", 12000L));
        queue.add(new Job("15号", 15000L));
        queue.add(new Job("10号", 10000L));
        queue.add(new Job("9号", 9000L));
        queue.add(new Job("8号", 8000L));
        // 新增入队
        queue.add(new Job("2号", 2000L));

        while (true) {
            Job poll = queue.poll();
            if (null == poll) {
                Thread.sleep(10);
                continue;
            }
            System.out.println(poll.getName());
        }
    }


}

class Job implements Delayed {
    private String name;
    private final Long begin;
    private final Long delayTime;

    public Job(String name, Long delayTime) {
        this.name = name;
        this.begin = System.currentTimeMillis();
        this.delayTime = delayTime;//延时时长
    }

    /**
     * 获取当前剩余的延迟时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        //大于0则表示时间已经到了，可以被取出
        return unit.convert(begin + delayTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Delayed o) {
        Job job = (Job) o;
        return (int) (this.getDelay(TimeUnit.MICROSECONDS) - job.getDelay(TimeUnit.MICROSECONDS));
    }
}

