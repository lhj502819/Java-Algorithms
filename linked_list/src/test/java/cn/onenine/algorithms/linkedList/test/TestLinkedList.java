package cn.onenine.algorithms.linkedList.test;

import cn.onenine.algorithms.linkedList.LinkedList;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 11:42
 */
public class TestLinkedList {

    public static void main(String[] args) {
        LinkedList<String> stringLinkedList = new LinkedList<String>();
        stringLinkedList.addFirst("1");
        stringLinkedList.addLast("2");
        stringLinkedList.addLast("3");
        stringLinkedList.addLast("4");
        stringLinkedList.addLast("5");

        //打印内容
        stringLinkedList.printList();

        System.out.println("-----------------------------------\r\n");
        //移除一个
        stringLinkedList.remove("4");
        //打印内容
        stringLinkedList.printList();
    }
}

