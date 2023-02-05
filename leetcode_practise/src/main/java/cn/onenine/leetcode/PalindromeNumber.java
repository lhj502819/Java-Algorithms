package cn.onenine.leetcode;

/**
 * Description：回文数 leetcode 9题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/5 22:35
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome2(121));
    }

    /**
     * 字符串解法
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        //11  121/222  1221/2222
        String value = String.valueOf(x);
        StringBuilder reverse = new StringBuilder(value).reverse();
        return value.equals(reverse.toString());
    }


    /**
     * 数字反转解法-优雅
     * <p>
     * 对于数字的末位，直接取余就可以了，对于数字的首位，我们可以这么算。
     * 首先用一个变量记录数字的最高位，
     * 比如 1232112321，可以标记 help 为 1000010000，
     * 第一个末位为 11，第一个首位为 12321/10000=1，
     * 接下来我们需要计算 232232 是否为回文，怎么计算呢？
     * 我们需要去掉首位和末位。
     * 可以采用 x % help / 10 的方式
     * 12321%10000==2321 可以将最高位去掉，然后 2321/10==232 可以将最低为去掉。
     * 最后不要忘记将 help/100。
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        int n = 1;
        int temp = x;
        while (temp >= 10) {
            n *= 10;
            temp /= 10;
        }

        while (x != 0) {
            if (x % 10 != x / n) {
                return false;
            }
            //x % n 去掉第一位
            // /10 去掉最后一位
            x = x % n / 10;
            n /= 100;
        }
        return true;
    }


}
