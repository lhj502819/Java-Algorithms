堆栈是计算机科学中的一种抽象数据类型，只允许在有序的线性集合的一端（称为堆栈顶端）添（push）数据和移除（pop）数据。

- 后进先出（LIFO，Last In First Out）的原理
- 堆栈常用一维数组或者链表来实现
  <a name="oqoiw"></a>
# 注意
请勿使用Java提供的`Stack`API，因为这个工具类是在JDK1.0阶段开发的，实现特别粗糙，同时在类注释上也提醒了使用`ArrayDeque`。
<a name="ckQME"></a>
# `ArrayDeque`

- 无容量上限，会根据需要进行扩容；
- 非线程安全，不支持并发访问；
- 不允许`null`元素；
- 当用作堆栈时比`Stack`快，当用作队列时，比`LinkedList`快；
- 快速失败：当创建`iterator`迭代器后，除了通过迭代器自己的 {@ code remove} 方法，当修改`deque`内容时，都会抛出`ConcurrentModificationException`，**因此面对并发修改，迭代器会迅速的失败。**
  <a name="USHPl"></a>
### 实现源码
<br />
```java
public class ArrayDeque<E> implements Deque<E> {

    private final Logger logger = LoggerFactory.getLogger(ArrayDeque.class);

    /**
     * 存储双端队列元素的数组，双端队列的容量就是这个数组的长度，它总是 2 的幂。
     * 数组永远不允许变满，除非在 addX 方法中短暂地在变满后立即调整大小（请参阅 doubleCapacity），
     * 从而避免头部和尾部环绕以彼此相等。我们还保证所有不包含双端队列元素的数组单元始终为空。
     */
    transient Object[] elements;

    /**
     * 双端队列头部元素的索引（将被remove()或pop()删除的元素）；如果双端队列为空，则为等于tail的任意数组
     */
    transient int head;

    /**
     * 将下一个元素添加到双端队列尾部的索引（通过 addLast(E)、add(E) 或 push(E)）
     */
    transient int tail;

    public ArrayDeque() {
        elements = new Object[2];
    }

    @Override
    public void push(E e) {
        if(e == null){
            throw new NullPointerException();
        }
        //(head -1) & (elements.length - 1)
        //计算索引，找到数组的队尾
        elements[head = (head -1) & (elements.length - 1)] = e;
        logger.info("push.idx head：{}", head);
        if(head == tail){
            //扩容
            doubleCapacity();
        }
    }

    /**
     * 库容
     */
    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        //队列大小
        int n = elements.length;
        //队列大小 - 队列头index
        int r = n - p;
        int newCapacity = n << 1;
        if(newCapacity <0){
            throw new IllegalArgumentException("deque too big");
        }
        Object[] a = new Object[newCapacity];

        /*
         * src      - 源数组
         * srcPos   – 源数组中的起始位置
         * dest     - 目标数组
         * destPos  – 目标数据中的起始位置
         * length   – 要复制的数组元素的数量
         */

        // 第一次拷贝元素：[2、1、4、3] 将数组中的扩容后一半元素拷贝到新数组0开始往后的位置。拷贝4、3
        System.arraycopy(elements, p, a, 0, r);
        // 第二次拷贝元素：[2、1、4、3] 将数组中的前面一半数量的元素，拷贝到新数组后一半开始的位置往后。拷贝2、1
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }

    @Override
    public E pop() {
        int h = head;
        E result = (E)elements[h];
        if(result == null){
            return null;
        }
        elements[h] = null;
        head = (head + 1) & (elements.length -1) ;
        logger.info("pop.idx {} = {} & {}", head, Integer.toBinaryString(h + 1), Integer.toBinaryString(elements.length - 1));
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }
}
```
<a name="Bsk2y"></a>
### ArrayDeque入队流程

- 空间扩容以2的倍数进行操作，以此保证2的幂等;
- System.arraycopy 是操作数据迁移的本地方法，从源数组的某个指定位置，把元素迁移到新数组的指定位置和指定个数个元素
- 另外是数据迁移，以 [2、1、4、3] 举例
   - 第一次拷贝元素：[2、1、4、3] 将数组中的扩容后一半元素拷贝到新数组0开始往后的位置。拷贝4、3
   - 第二次拷贝元素：[2、1、4、3] 将数组中的前面一半数量的元素，拷贝到新数组后一半开始的位置往后。拷贝2、1

![image.png](https://cdn.nlark.com/yuque/0/2022/png/1171730/1669043296357-a1f27351-9e8a-43d3-9831-c8c1e15eee48.png#averageHue=%23faf8f8&clientId=ufd8f477e-a9e1-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=4173&id=u98babd28&margin=%5Bobject%20Object%5D&name=image.png&originHeight=5283&originWidth=1518&originalType=binary&ratio=1&rotation=0&showTitle=false&size=474498&status=done&style=none&taskId=u37edc99f-e5ed-4e13-bac5-4cc48163e6c&title=&width=1199)
<a name="m4Cp6"></a>
### ArrayDeque出队流程
head 的值从扩容的长度添加元素后逐步减小<br />![image.png](https://cdn.nlark.com/yuque/0/2022/png/1171730/1669043337254-56c4d280-f8cd-43a2-a056-abb6e32e63ba.png#averageHue=%23f8f4f1&clientId=ufd8f477e-a9e1-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=1635&id=u35de8f3f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1635&originWidth=1036&originalType=binary&ratio=1&rotation=0&showTitle=false&size=174621&status=done&style=none&taskId=uf49edbec-bca0-4f71-a50b-71fc94a7bad&title=&width=1036)
