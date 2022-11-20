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