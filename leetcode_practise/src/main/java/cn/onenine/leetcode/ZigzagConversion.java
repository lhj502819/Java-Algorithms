package cn.onenine.leetcode;

/**
 * Description：N 字形变换 leetcode 第6题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/1 23:03
 */
public class ZigzagConversion {

    /**
     * 通过flag控制走向，每一层都有一个String，最终将所有的String拼在一起
     */
    public String convert(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        StringBuilder[] ss = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            ss[i] = new StringBuilder();
        }

        //如果是1则往下走  如果是-1则网上走
        int flag = 1;
        int line = 0;
        for (int i = 0; i < s.length(); i++) {
            if (line == numRows - 1) {
                flag = -1;
            }
            if (line == 0){
                flag = 1;
            }
            ss[line].append(s.charAt(i));
            line += flag;
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < ss.length; i++) {
            res.append(ss[i]);
        }

        return res.toString();

    }

}
