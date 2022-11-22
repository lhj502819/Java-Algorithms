哈希表的存在是为了解决能通过O(1)时间复杂度直接索引到指定元素。<br />我们使用数组存放元素，都是按照**顺序存放**，当需要获取某个元素的时候，则需要对数组进行遍历，获取到指定的值。这样通过循环遍历的时间复杂度为**O(n)，**这样的时间复杂度并不是最优的，这就引入了哈希散列表的设计。

---

在计算机科学中，一个哈希表是一种实现关联数组的抽象数据结构，该结构将键通过哈希计算映射到值，这样时间复杂度就为O(1)。
<a name="mDKCf"></a>
# 哈希碰撞
哈希散列虽然解决了获取元素的时间复杂度问题，但大多数时候只是理想情况。因为随着元素的增多，很可能发生哈希冲突，或者哈希值波动不大导致索引计算相同，**也就是一个索引位置出现多个元素的情况。**
<a name="UH9Pm"></a>
# 解决方案

- HashMap中的拉链寻址 +红黑树
- 扰动因子
- 负载因子
- ThreadLocal的开放寻址
- 合并散列
- 杜鹃散列
- 跳房子哈希
- 罗宾汉哈希等。
  <a name="VFfw8"></a>
# 什么是装载因子？
为了尽可能保证散列表的操作效率，一般情况下，我们会尽可能保证散列表中有一定比例的空闲槽位，因此用装载因子来表示空位的多少。**计算公式为：**`**表中的元素个数 / 散列表的长度**`
<a name="phq9P"></a>
# 拉链寻址
```java
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
```
<a name="kDaMU"></a>
# 开放寻址
开放寻址会将所有的元素都存放在哈希桶中，就算冲突也会放。**对碰撞的元素会再次寻找哈希桶上新的位置，这个位置是从当前碰撞的位置开始向后找，直到找到空的位置存放.**
<a name="uQ34i"></a>
## 优缺点

- 优点：
  - 散列表中的数据都存储在数组中，可以有效地利用CPU缓存加速查询
  - 数组实现的散列表，序列化起来比较简单。而链表法包含指针，序列化起来就没有那么容易。
- 缺点：
  - 删除数据麻烦，如果直接删除元素，那可能就影响删除的槽位后边的元素查找，只能将删除的元素进行特殊标记
  - 所有的数据都存储在一个数组中，比起链表来说，冲突的代价更高
  - 装载因子的上限不能太大，导致这种方法比链表法更浪费内存空间
    <a name="LxIAc"></a>
## 代码实现
```java
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
```
<a name="nnsyP"></a>
# 合并散列
合并散列是拉链法和开放寻址法的结合，碰撞的节点在哈希桶中链接，适合在固定分配内存的哈希桶，通过存放元素时识别哈希桶上最大的空槽位来解决合并哈希中的冲突。**解决了开发寻址法因为寻找碰撞元素所发生的循环遍历。**
<a name="QEfHb"></a>
## 代码实现
```java
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
```
<a name="HnhOm"></a>
# 杜鹃散列
由来：杜鹃鸟在孵化的时候，雏鸟会将其他的蛋或者幼崽推出巢穴，类似这个数据结构会使用两组哈希表一样，将冲突元素推到另外一个哈希表中。

- 当多个键映射到同一个单元格时会发生这种情况，杜鹃散列的基本思想是通过使用两个散列函数而不是一个散列函数来解决冲突。
- 为每个键在哈希表中提供了两个可能的位置，在该算法的一种**常用变体**中，哈希表被分成两个大小相等的较小的表，每个哈希函数都为这两个表之一提供索引。两个散列函数也可以为单个表提供索引。
- 实践中，杜鹃哈希比线性探测慢约20%~30%，线性探测是常用方法中最快的。然而由于它对搜索时间的最坏情况保证，当需要实时响应率时，杜鹃散列仍然很有价值，**优点是它的无链接列表属性，非常适合GPU处理。**

<br />
<a name="qt9ph"></a>
## 实现代码
```java
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
```

<a name="TFWrc"></a>
# 跳房子散列
跳房子散列是一种基于开放寻址的算法，它结合了杜鹃散列、线性探测和链接的元素，通过桶邻域的概念——任何给定占用桶周围的后续桶，也称为“虚拟”桶。 该算法旨在在哈希表的负载因子增长超过 90% 时提供更好的性能；它还在并发设置中提供了高吞吐量，因此非常适合实现可调整大小的并发哈希表。

<a name="wBiNT"></a>
# 罗宾汉哈希
罗宾汉哈希是一种基于开放寻址的冲突解决算法；冲突是通过偏向从其“原始位置”（即项目被散列到的存储桶）最远或最长探测序列长度（PSL）的元素的位移来解决的。
