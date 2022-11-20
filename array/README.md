# 数组

数组是一种线性表数据结构，它用一组连续的内存空间，来存储一组**具有相同类型**数据的集合。

## 特点

1. 数组是相同数据类型的元素集合
2. 数组中各个元素的存储是又先后顺序的，他们在内存中按照这个顺序连续存放到一起，内存地址连续。
3. 数组获取元素的时间复杂度是O(1)

## 一维数组

一维数组是最常用的数组，其他很多的数据结构变种都是从一维数组来的。例如`HashMap`拉链寻址结构，`ThreadLocal`的开放寻址结构，都是从一维数组上实现的。



## 二维数组

二维以及多维数组，在开发场景中使用到的并不多，不过在一些算法逻辑和数学计算中倒是会使用到。



## 实现数组列表（ArrayList）

```
package cn.onenine.algorithms.arraylist;

import java.util.Arrays;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 12:22
 */
public class ArrayList<E> {

    /**
     * 初始容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 默认容量下的空数组
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};


    /**
     * ArrayList 元素数组缓存区
     */
    transient Object[] elementData;

    int size = 0;

    public ArrayList() {
        elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    private ArrayList(int size) {
        elementData = new Object[size];
    }

    /**
     * 添加元素
     */
    public boolean add(E e) {
        //确保内部容量，先计算size
        int minCapacity = size + 1;
        if (elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        //判断扩容操作
        if (minCapacity - elementData.length > 0) {
            //需要扩容
            //计算新的数组大小
            int oldCapacity = elementData.length;
            int newCapacity = (elementData.length >> 1) + oldCapacity;
            if (newCapacity - minCapacity < 0) {
                //还是不够
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        //添加元素
        elementData[size++] = e;
        return true;

    }

    /**
     * 移除元素
     * @param index 被移除元素下标
     */
    public E remove(int index){
        if(index >= size){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E element = (E) elementData[index];
        //要被移动的元素个数
        int numMoved = size - index - 1;
        if(numMoved > 0){
            //从原始数组的index+1位置将后边的元素拷贝到index位置
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return element;

    }

    /**
     * 获取元素
     * @param index
     * @return
     */
    public E get(int index){
        return (E) elementData[index];
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
```

## 相关面试题

1. 数据结构中有哪些是线性表数据结构？
    - 数组、栈、队列

2. 数组的元素删除和获取，时间复杂度分别为多少？

    - 获取的时间复杂度为o(1)

    - 删除的时间复杂度在最坏的情况下是删除第一位，需要将后边的（n-1）个元素全部向前移动一位，因此需要o(n)

3. ArrayList 中默认的初始化长度是多少？

    - 10

4. ArrayList 中扩容的范围是多大一次？

    - 在每次存入元素的时候会进行判断是否超过最大容量，如果超过了则以原容量的1.5倍进行扩容

5. ArrayList 是如何完成扩容的，System.arraycopy 各个入参的作用是什么？

    - 扩容的关键方法是grow()，当添加元素时判断是否超过最大容量，如果超过了则进行扩容，首先会创建一个新的数组，将旧的数据拷贝到新数组，这样有了副本，接下的操作不会影响到原数据，然后通过将容量扩容为原大小的1.5倍，最后调用Arrays.copyOf方法将elementData数组指向新的内存空间时newCapacity的连续空间。System.arraycopy 各个入参的作用简单而言就是将第一个参数的数组中的某一片段数据复制到第二个数组当中。