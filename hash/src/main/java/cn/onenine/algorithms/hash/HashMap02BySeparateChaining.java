package cn.onenine.algorithms.hash;

import java.util.LinkedList;

/**
 * Description：通过拉链寻址解决哈希冲突
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/22 21:25
 */
public class HashMap02BySeparateChaining<K,V>  implements Map<K,V>{

    private final LinkedList<Node<K,V>>[] tab = new LinkedList[10];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if(tab[idx] == null){
            //没有发生哈希碰撞
            tab[idx] = new LinkedList<Node<K,V>>();
            tab[idx].add(new Node<>(key,value));
        }else {
            //哈希碰撞了
            tab[idx].add(new Node<>(key,value));
        }
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        if(tab[idx].size() == 1){
            return tab[idx].peek().getValue();
        }
        for (Node<K, V> kvNode : tab[idx]) {
            if (key.equals(kvNode.getKey())) {
                return kvNode.value;
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
