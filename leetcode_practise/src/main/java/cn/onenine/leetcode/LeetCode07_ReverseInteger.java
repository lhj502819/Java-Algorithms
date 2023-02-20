package cn.onenine.leetcode;

/**
 * Description：整数反转
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/20 21:01
 */
public class LeetCode07_ReverseInteger {

    public int reverse(int x) {
        //不管正数还是负数，都当做负数处理
        boolean neg = x < 0;
        x = neg ? x : -x;

        //边界判断
        //因下方我们是将res * 10，因此我们这里对MIN_VALUE/10，如果res小于了MIN_VALUE/10，那res * 10之后，由于是负数，会比MIN_VALUE更小因此越界
        int m = Integer.MIN_VALUE / 10;
        //因下方将res * 10 之后还要加上x%10的结果，如果res == MIN_VALUE / 10时，x%10>=MIN_VALUE % 10，那res会>=MIN_VALUE，不会越界，
        //  但如果x%10<MIN_VALUE % 10，那res会<MIN_VALUE，则越界，因此越界判断可写为
        //      if (res < m || res == m && x % 10 < o)
        int o = Integer.MIN_VALUE % 10;

        int res = 0;

        while (x != 0) {
            if (res < m || res == m && x % 10 < o)
                return 0;
            //以下两步，每次均可将最后一位数字加到res最后一位
            res = res * 10 + x % 10;
            x /= 10;
        }
        //如果原来的数字是负数，直接返回，否则取绝对值返回
        return neg ? res : Math.abs(res);
    }
}
