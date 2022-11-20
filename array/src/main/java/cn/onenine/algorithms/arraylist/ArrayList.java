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
