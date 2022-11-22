package cn.onenine.algorithms.hash;

/**
 * Description：开放寻址法解决哈希冲突
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/22 22:07
 */
public class HashMap03ByOpenAddressing<K,V> implements Map<K,V>{

    Node<K,V>[] tab = new Node[10];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if(tab[idx] == null){
            tab[idx] = new Node<>(key,value);
        }else {
            for (int i = idx + 1; i < tab.length; i++) {
                if(tab[i] == null){
                    tab[i] = new Node<>(key,value);
                    break;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        for (int i = idx ; i < tab.length; i++) {
            if(tab[i] != null && tab[i].key.equals(key)){
                return tab[i].getValue();
            }
        }
        return null;
    }

    private static class Node<K,V> {

        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
