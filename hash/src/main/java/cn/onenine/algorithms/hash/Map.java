package cn.onenine.algorithms.hash;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/22 21:22
 */
public interface Map<K, V> {

    void put(K key, V value);

    V get(K key);

}
