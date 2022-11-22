package cn.onenine.algorithms.hash;


/**
 * Description：模拟哈希碰撞
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/22 21:21
 */
public class HashMap01<K,V> implements Map<K, V> {

    private final Object[] tab = new Object[8];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        tab[idx] = value;
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        return (V) tab[idx];
    }
}

