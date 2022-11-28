package cn.onenine.algorithms.heap.test;


import org.junit.Test;

import java.util.Arrays;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 11:42
 */
public class TestLinkedList {

    public static void main(String[] args) {
        int[] i = new int[3];
        i[0] = 1;
        i[1] = 2;
        i[2] = 3;

        int[] j = new int[6];
        System.arraycopy(i,0,j,4,3);
        System.out.println(Arrays.toString(j));
    }


    @Test
    public void testArrayList(){
        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList.add("1");
        stringArrayList.add("2");
        stringArrayList.add("3");
        stringArrayList.add("4");
        stringArrayList.add("5");
        stringArrayList.add("6");
        stringArrayList.add("7");
        stringArrayList.add("8");
        stringArrayList.add("9");
        stringArrayList.add("10");
        System.out.println(stringArrayList);

        stringArrayList.remove(0);
        System.out.println(stringArrayList);
    }
}

