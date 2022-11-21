package cn.onenine.algorithms.stack.test;

import cn.onenine.algorithms.stack.ArrayDeque;
import cn.onenine.algorithms.stack.Deque;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/21 23:03
 */
public class AppTest {

    private final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void testArrayDeque() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.push(4);
        deque.push(5);
        deque.push(6);
        deque.push(7);
        logger.info("弹出元素：{}", deque.pop());
        logger.info("弹出元素：{}", deque.pop());
        logger.info("弹出元素：{}", deque.pop());
        logger.info("弹出元素：{}", deque.pop());
        logger.info("弹出元素：{}", deque.pop());
        logger.info("弹出元素：{}", deque.pop());
        logger.info("弹出元素：{}", deque.pop());
    }

}
