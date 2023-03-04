package cn.onenine.leetcode;

/**
 * Description：两数相除
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/3/4 11:34
 */
public class LeetCode29_Divide_Two_Integers {


    /**
     *
     * a - b = a + ( - b)
     *      -b = ~b + 1
     *
     * a-b = a +(~b + 1)
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
         return dividend /divisor;
    }

    /**
     * 无进位相加的信息 + 进位信息，一直加到没有进位为止
     */

}
