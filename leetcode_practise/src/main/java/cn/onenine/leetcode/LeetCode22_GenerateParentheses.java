package cn.onenine.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：括号生成
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/26 10:38
 */
public class LeetCode22_GenerateParentheses {

    public static void main(String[] args) {
        LeetCode22_GenerateParentheses leetCode22GenerateParentheses = new LeetCode22_GenerateParentheses();
        List<String> strings = leetCode22GenerateParentheses.generateParenthesis(3);
        System.out.println(strings);
    }


    public List<String> generateParenthesis(int n) {

        List<String> ans = new ArrayList<>();


        process("", n, n, ans);
        return ans;
    }

    /**
     * 这里搞不懂的点是，当遍历完((()))这种情况之后，下一次会返回到哪里
     * 这时(((的这种情况已经遍历完了，就要遍历((这种情况了，并且是走到if (right > left) 的判断，此时left=1,right=3,自然会走到(()，接着继续
     * 可以看LeetCode22_pic.png
     */
    public void process(String subList, int left, int right, List<String> ans) {
        if (left == 0 && right == 0) {
            ans.add(subList);
        } else {
            //只要有左括号，就加左括号
            if (left > 0) {
                process(subList + "(", left - 1, right, ans);
            }
            //如果右括号剩余的大于左括号，就加右括号
            if (right > left) {
                process(subList + ")", left, right - 1, ans);
            }
        }
    }

}
