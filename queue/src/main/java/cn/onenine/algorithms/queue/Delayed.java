package cn.onenine.algorithms.queue;

import java.util.concurrent.TimeUnit;

/**
 * Description：延迟信息接口
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:42
 */
public interface Delayed extends Comparable<Delayed> {

    /**
     * 获取延迟时间
     * @param unit
     * @return
     */
    long getDelay(TimeUnit unit);

}
