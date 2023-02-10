package cn.onenine.leetcode;


/**
 * Description：盛最多的水  leetcode 11题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/6 20:39
 */
public class ContainerWithMostWater {

    /**
     * 题解 ：消状态法，从两边向中间缩进，i,j，长的一边往中间缩进，可能会变长，但面积取决于短的一边，因此长的变得更长也没用，并且底边还变短了，因此只能移动短边，才有可能使面积变大
     * https://leetcode.cn/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
     */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int area = 0;
        while (i < j) {
            area = Math.max(area, Math.min(height[i], height[j])* (j - i)) ;
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return area;
    }

}
