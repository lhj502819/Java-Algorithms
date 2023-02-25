package cn.onenine.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description：有效的括号
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/25 15:30
 */
public class LeetCode20_ValidParenthesis {

    public static void main(String[] args) {
        LeetCode20_ValidParenthesis leetCode20ValidParenthesis = new LeetCode20_ValidParenthesis();
        leetCode20ValidParenthesis.isValid("()");
    }

    /**
     * 使用栈解决，遇到左括号就入栈，遇到右括号就出栈，出栈之后判断是否和有括号匹配；但需要注意的是第一个符号就是有括号，那此时出栈之后是空的，需要进行判空
     * 最终还要判断栈是否为空
     */
    public boolean isValid(String s) {
        if(s == null || s.length() == 0){
            return false;
        }

        Stack<Character> stack = new Stack<>();

        Map<Character,Character> pair = new HashMap<>();
        pair.put('(',')');
        pair.put('[',']');
        pair.put('{','}');
        //()[]
        for (int i = 0; i < s.length(); i++) {
            if (pair.containsKey(s.charAt(i))){
                stack.push(s.charAt(i));
            }else {
                if (stack.isEmpty() || !pair.get(stack.pop()).equals(s.charAt(i))){
                    return false;
                }
            }
        }
        return stack.isEmpty();


    }

}
