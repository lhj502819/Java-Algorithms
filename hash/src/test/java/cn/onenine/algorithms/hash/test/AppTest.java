package cn.onenine.algorithms.hash.test;

import cn.onenine.algorithms.hash.*;
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
    public void testHashMap01() {
        Map<String, String> map = new HashMap01<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key：{} value：{}", "01", map.get("01"));
    }

    @Test
    public void testHashMap02BySeparateChaining(){
        Map<String, String> map = new HashMap02BySeparateChaining<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key：{} value：{}", "01", map.get("01"));
    }

    @Test
    public void testHashMap03ByOpenAddressing(){
        Map<String, String> map = new HashMap03ByOpenAddressing<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key：{} value：{}", "01", map.get("01"));
    }

    @Test
    public void testHashMap04ByCoalescedHashing(){
        Map<String, String> map = new HashMap04ByCoalescedHashing<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key：{} value：{}", "01", map.get("01"));
    }

    @Test
    public void testHashMap05ByCuckooHashing(){
        java.util.Map<String, String> map = new HashMap05ByCuckooHashing<>();
        map.put("01", "花花");
        map.put("05", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("12"));
        logger.info("数据结构：{}", map);
    }
}
