# 队列（Queue）
队列是一种特殊类型的抽象数据类型或者集合（可以用链表实现，也可以用数组实现）。是一种**FIFO的模式。**<br />将元素添加到队列后的操作称为入队，从队列中移除元素的操作称为出队。另外还分为**单端队列和双端队列。**
<a name="P68Ey"></a>
# 队列的数据结构

- 从理论上将，队列是没有特定容量的，不管已经包含多少个元素，总是可以再添加一个新元素；
- **既可以是数组实现、也可以链表实现**
- Java中`Deque`是双端队列接口，`Queue`是单端队列接口

<br />
<a name="ERZAt"></a>
# 实现延迟队列
`DelayQueue`是一个BlockingQueue(无界队列)，它封装了一个使用完全二叉堆排序元素的`PriorityQueue(优先队列)`。在添加元素时使用`Delay(延迟时间)`作为排序条件，延迟最小的元素会优先放到队首。
<a name="ePHMo"></a>
## 二叉堆
二叉堆是一种特殊结构的堆，它的表现形态可以是一颗完整或者近似二叉树的结构。延迟队列中的元素存放，使用的就是`PriorityQueue`实现的平衡二叉堆结构，数据以队列形式存放在基础数组中。<br />![image.png](https://cdn.nlark.com/yuque/0/2022/png/1171730/1668935540631-0e99580c-bbc6-4351-bcb8-6f7fc814e3c3.png#averageHue=%23fbfbfb&clientId=u8997ff9e-9023-4&crop=0&crop=0&crop=1&crop=1&from=paste&id=ua41fa96b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=732&originWidth=1062&originalType=url&ratio=1&rotation=0&showTitle=false&size=93297&status=done&style=none&taskId=u13c4294d-4c09-4527-8cbf-3788bcbf637&title=)

- 父子节点索引关系：
   - 假如父节点为queue[n]，那么左子节点为queue[2n+1]，右子节点为queue[2n+2]
   - 任意孩子节点的父节点位置，都是`(n-1)>>>1`，相当于减1后除2取整
- 节点间大小关系：
   - 父节点小于等于任意孩子节点
   - 同一层级的两个孩子节点大小不需要维护，它是在弹出元素的时候进行判断额
- 叶子节点与非叶子节点：
   - 一个长度为`size`的优先级队列，当`index >= size >>> 1`时，该节点为叶子节点，否则为非叶子结点
- 延迟队列的使用是以在`DelayQueue`中存放实现了`Delayed`延迟接口的对象，因为只有这个对象，才能比较出当前元素与所需存放到对应位置的一个比对计算过程；

<a name="WJtLa"></a>
## 核心类
```java
/**
 * Description：优先级队列
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 16:36
 */
public class PriorityQueue<E> implements Queue<E> {

    private Logger logger = LoggerFactory.getLogger(PriorityQueue.class);

    private final int DEFAULT_INITIAL_CAPACITY = 10;

    transient Object[] queue;

    private int size = 0;

    public PriorityQueue() {
        queue = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        //当前队列大小
        int i = size;
        //先判断是否需要扩容发
        if (i >= queue.length) {
            //扩容
            grow(i + 1);
        }
        //队列大小+1
        size = i + 1;
        if (i == 0) {
            //如果是第一个元素，则直接放到0号位置
            queue[0] = e;
        } else {
            //否则按照二叉堆规则插入
            siftUp(i, e);
        }
        return true;
    }

    private void siftUp(int k, E x) {
        siftUpComparable(k, x);
    }

    /**
     * 二叉堆在存放元素时，会遵循它的特点，在存放过程中，通过队尾元素向上对比迁移，上浮操作
     */
    @SuppressWarnings("uncheck")
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        logger.info("【入队】元素：{} 当前队列：{}", com.alibaba.fastjson.JSON.toJSONString(key), com.alibaba.fastjson.JSON.toJSONString(queue));
        while (k > 0) {
            // 获取父节点Idx，相当于除以2
            int parent = (k - 1) >>> 1;
            logger.info("【入队】寻找当前节点的父节点位置。k：{} parent：{}", k, parent);
            Object e = queue[parent];
            // 如果当前位置元素，大于父节点元素，则退出循环
            if (key.compareTo((E) e) >= 0) {
                logger.info("【入队】值比对，父节点：{} 目标节点：{}", com.alibaba.fastjson.JSON.toJSONString(e), com.alibaba.fastjson.JSON.toJSONString(key));
                break;
            }
            // 相反父节点位置大于当前位置元素，则进行替换
            logger.info("【入队】替换过程，父子节点位置替换，继续循环。父节点值：{} 存放到位置：{}", com.alibaba.fastjson.JSON.toJSONString(e), k);
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
        logger.info("【入队】完成 Idx：{} Val：{} \r\n当前队列：{} \r\n", k, com.alibaba.fastjson.JSON.toJSONString(key), JSON.toJSONString(queue));
    }

    /**
     * 扩容
     */
    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - (Integer.MAX_VALUE - 8) > 0)
            newCapacity = (minCapacity > Integer.MAX_VALUE - 8) ?
                    Integer.MAX_VALUE :
                    Integer.MAX_VALUE - 8;
        queue = Arrays.copyOf(queue, newCapacity);
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        //队列大小 - 1
        int s = --size;
        //拿到队列头的元素，这个元素是最小的
        E result = (E) queue[0];
        //获取队尾的元素(最大的)
        E x = (E) queue[s];
        //将队列的尾元素设置为null
        queue[s] = null;
        if (s != 0) {
            //如果队列还有元素
            siftDown(0, x);
        }
        return result;
    }

    private void siftDown(int k, E x) {
        siftDownComparable(k, x);
    }

    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        // 先找出中间件节点
        int half = size >>> 1;
        while (k < half) {
            // 找到左子节点和右子节点，两个节点进行比较，找出最大的值
            int child = (k << 1) + 1;
            Object left = queue[child];
            int right = child + 1;
            // 左子节点与右子节点比较，取最小的节点
            if (right < size && ((Comparable<? super E>) left).compareTo((E) queue[right]) > 0) {
                logger.info("【出队】左右子节点比对，获取最小值。left：{} right：{}", com.alibaba.fastjson.JSON.toJSONString(left), com.alibaba.fastjson.JSON.toJSONString(queue[right]));
                left = queue[child = right];
            }
            // 目标值与left比较，当目标值小于left值，退出循环。说明此时目标值所在位置适合，迁移完成。
            if (key.compareTo((E) left) <= 0) {
                break;
            }
            // 目标值小于left值，位置替换，继续比较
            logger.info("【出队】替换过程，节点的值比对。上节点：{} 下节点：{} 位置替换", com.alibaba.fastjson.JSON.toJSONString(queue[k]), com.alibaba.fastjson.JSON.toJSONString(left));
            queue[k] = left;
            k = child;
        }
        // 把目标值放到对应位置
        logger.info("【出队】替换结果，最终更换位置。Idx：{} Val：{}", k, com.alibaba.fastjson.JSON.toJSONString(key));
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }
}

```

```java
public static void main(String[] args) throws InterruptedException {
        Queue<Job> queue = new DelayQueue<Job>();
        queue.add(new Job("1号", 1000L));
        queue.add(new Job("3号", 3000L));
        queue.add(new Job("5号", 5000L));
        queue.add(new Job("11号", 11000L));
        queue.add(new Job("4号", 4000L));
        queue.add(new Job("6号", 6000L));
        queue.add(new Job("7号", 7000L));
        queue.add(new Job("12号", 12000L));
        queue.add(new Job("15号", 15000L));
        queue.add(new Job("10号", 10000L));
        queue.add(new Job("9号", 9000L));
        queue.add(new Job("8号", 8000L));
        // 新增入队
        queue.add(new Job("2号", 2000L));

        while (true) {
            Job poll = queue.poll();
            if (null == poll) {
                Thread.sleep(10);
                continue;
            }
            System.out.println(poll.getName());
        }
    }
```
<a name="Qag79"></a>
# offer流程
相对来说比较简单，不再阐述，按照节点索引关系公式计算即可，数据结构如图<br />![image.png](https://cdn.nlark.com/yuque/0/2022/png/1171730/1668954633004-7bd99c9b-0837-4738-b596-85ddcd9cac84.png#averageHue=%23f7f7f7&clientId=u8997ff9e-9023-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=633&id=u126b0795&margin=%5Bobject%20Object%5D&name=image.png&originHeight=633&originWidth=1075&originalType=binary&ratio=1&rotation=0&showTitle=false&size=90335&status=done&style=none&taskId=u85a49014-323c-4d77-8fd7-c0b92b01b31&title=&width=1075)
<a name="WT2DR"></a>
# poll流程
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1171730/1668954375248-a1eb8034-84c1-4738-b304-b2e55709cfcb.png#averageHue=%23f7f4f3&clientId=u8997ff9e-9023-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=645&id=u2b805e2e&margin=%5Bobject%20Object%5D&name=image.png&originHeight=645&originWidth=1150&originalType=binary&ratio=1&rotation=0&showTitle=false&size=126950&status=done&style=none&taskId=uea0f6d3b-42fb-4c68-ac0a-866109c3d4e&title=&width=1150)<br />![image.png](https://cdn.nlark.com/yuque/0/2022/png/1171730/1668954417372-125b64e3-ed0c-45c7-9527-d788a351be01.png#averageHue=%23f6f5f3&clientId=u8997ff9e-9023-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=628&id=u793d9eec&margin=%5Bobject%20Object%5D&name=image.png&originHeight=628&originWidth=1002&originalType=binary&ratio=1&rotation=0&showTitle=false&size=107489&status=done&style=none&taskId=ua3b87834-b6ff-4257-bf67-ec42026c333&title=&width=1002)




![image.png](https://cdn.nlark.com/yuque/0/2022/png/1171730/1668954446822-f6225704-802d-4755-8c55-9aefd1671c9d.png#averageHue=%23f6f5f5&clientId=u8997ff9e-9023-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=625&id=ud7e82faa&margin=%5Bobject%20Object%5D&name=image.png&originHeight=625&originWidth=999&originalType=binary&ratio=1&rotation=0&showTitle=false&size=102365&status=done&style=none&taskId=ucbba843d-1f15-4919-ba7e-5f6066cc4ac&title=&width=999)



