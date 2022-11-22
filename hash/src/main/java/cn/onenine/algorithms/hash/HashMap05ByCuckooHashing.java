package cn.onenine.algorithms.hash;

import java.util.*;
import java.util.Map;

/**
 * Description：杜鹃散列解决哈希冲突
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/22 22:45
 */
public class HashMap05ByCuckooHashing<K,V> extends AbstractMap<K, V> implements Map<K, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 8;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    transient Entry<K, V>[] table;

    transient int size;

    int threshold;

    final float loadFactor;

    final transient HashFunction<K> hash1;

    final transient HashFunction<K> hash2;

    static final Object NULL_KEY = new Object();

    public HashMap05ByCuckooHashing() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
        hash1 = new DefaultHashFunction<>(2);
        hash2 = new DefaultHashFunction<>(3);
    }

    static <T> T maskNull(T key) {
        return key == null ? (T) NULL_KEY : key;
    }

    static <T> T unmaskNull(T key) {
        return (key == NULL_KEY ? null : key);
    }


    interface HashFunction<T> {
        int hash(Object key, int limit);
    }

    static class DefaultHashFunction<T> implements HashFunction<T> {

        private static final Random ENGINE = new Random();
        private int rounds;

        public DefaultHashFunction(int rounds) {
            this.rounds = rounds;
        }

        public int hash(Object key, int limit) {
            ENGINE.setSeed(key.hashCode());
            int h = ENGINE.nextInt(limit);
            for (int i = 1; i < this.rounds; i++) {
                h = ENGINE.nextInt(limit);
            }
            return h;
        }

    }

    static class Entry<K, V> implements java.util.Map.Entry<K, V> {
        final K key;
        V value;

        Entry(K k, V v) {
            value = v;
            key = k;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof java.util.Map.Entry))
                return false;
            java.util.Map.Entry e = (java.util.Map.Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (Objects.equals(k1, k2)) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                return Objects.equals(v1, v2);
            }
            return false;
        }

        public final K getKey() {
            return HashMap05ByCuckooHashing.unmaskNull(key);
        }

        public final V getValue() {
            return value;
        }

        public final int hashCode() {
            return (key == null ? 0 : key.hashCode())
                    ^ (value == null ? 0 : value.hashCode());
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final String toString() {
            return getKey() + "=>" + getValue();
        }
    }



    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = new HashSet<Map.Entry<K, V>>();
        for (Entry<K, V> e : table) {
            if (e != null) {
                es.add(e);
            }
        }

        return es;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        return put(key, value, false);
    }

    private V put(K key, V value, boolean isRehash) {
        Object k = maskNull(key);

        if (containsKey(k)) {
            return null;
        }

        if (insertEntry(new Entry<K, V>((K) k, value))) {
            if (!isRehash) {
                size++;
            }

            return null;
        }

        rehash(2 * table.length);
        return put((K) k, value);
    }

    private void rehash(int newCapacity) {
        Entry<K, V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry<K, V>[] newTable = new Entry[newCapacity];
        table = newTable;
        for (Entry<K, V> e : oldTable) {
            if (e != null) {
                put(e.key, e.value, true);
            }
        }

        threshold = (int) (newCapacity * loadFactor);
    }

    public int size() {
        return size;
    }

    @Override
    public V get(Object key) {
        Object k = maskNull(key);

        int hash = hash(hash1, k);
        Object k2;
        Entry<K, V> e = table[hash];
        if (e != null && ((k2 = e.key) == k || k.equals(k2))) {
            return e.value;
        }

        hash = hash(hash2, k);
        e = table[hash];
        if (e != null && ((k2 = e.key) == k || k.equals(k2))) {
            return e.value;
        }

        return null;
    }

    private int hash(HashFunction<K> func, Object key) {
        return func.hash(key, table.length);
    }

    private boolean insertEntry(Entry<K, V> e) {
        int count = 0;
        Entry<K, V> current = e;
        int index = hash(hash1, current.key);
        while (current != e || count < table.length) {
            Entry<K, V> temp = table[index];
            if (temp == null) {
                //没有哈希冲突
                table[index] = current;
                return true;
            }
            //哈希冲突
            table[index] = current;
            current = temp;
            if (index == hash(hash1, current.key)) {
                index = hash(hash2, current.key);
            } else {
                index = hash(hash1, current.key);
            }

            ++count;
        }

        return false;
    }
}
