package cn.onenine.algorithms.hash;

/**
 * Description：合并散列解决哈希冲突
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/22 22:32
 */
public class HashMap04ByCoalescedHashing<K,V> implements Map<K,V>{

    Node<K,V>[] tab = new Node[10];
    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if(tab[idx] == null){
            tab[idx] = new Node<>(key,value);
        }
        int cursor = tab.length -1;
        while (tab[cursor] != null && tab[cursor].key != key){
            cursor--;
        }

        tab[cursor] = new Node<>(key,value);

        // 将碰撞节点指向这个新节点，这里是判断当前节点的next是否已经有节点了，有的话需要一直往后遍历，直到next节点为空
        while (tab[idx].nextIdNode != 0){
            idx = tab[idx].nextIdNode;
        }

        tab[idx].nextIdNode = cursor;

    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        while (tab[idx].key != key) {
            idx = tab[idx].nextIdNode;
        }
        return tab[idx].value;
    }

    private static class Node<K,V> {

        private K key;
        private V value;

        private int nextIdNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setNextIdNode(int nextIdNode) {
            this.nextIdNode = nextIdNode;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
