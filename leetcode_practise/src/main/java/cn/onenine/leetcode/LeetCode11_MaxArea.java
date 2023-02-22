package cn.onenine.leetcode;

/**
 * Description：盛最多的水
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/21 22:15
 */
public class LeetCode11_MaxArea {

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;
        while (i < j) {
            Math.max((j - i) * Math.min(height[i], height[j]), maxArea);
            if (height[i] > height[j] ){
                j -- ;
            }else{
                i++;
            }
        }

        return maxArea;
    }
}
