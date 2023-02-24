package cn.onenine.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：电话号码的字母组合
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/24 22:02
 */
public class LeetCode17_CombinationOfPhoneNumbers {

    public static char[][] phone = new char[][]{
            {'a', 'b', 'c'},//2
            {'d', 'e', 'f'},//3
            {'g', 'h', 'i'},//4
            {'j', 'k', 'l'},//5
            {'m', 'n', 'o'},//6
            {'p', 'q', 'r', 's'},//7
            {'t', 'u', 'v'},//8
            {'w', 'x', 'y', 'z'},//9
    };


    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return ans;
        }

        char[] chars = digits.toCharArray();
        //path表示一次遍历的路径
        //比如digits 23
        //2:abc
        //3:def
        //那每个组合的结果长度都为2，如ad、ae、af;bd、be、bf;cd、ce、cf
        //需要从第一个数字的第一个单词开始遍历，每个单词都要再和下一个数字的所有单词组合，下一个数字的所有单词都要和下下一个数字的单词进行组合
        //其实就是一个递归，每次递归到数字的长度截止
        char[] path = new char[digits.length()];
        process(chars, 0, path, ans);
        return ans;
    }

    public void process(char[] digits, int index, char[] path, List<String> ans) {
        if (index == digits.length){
            ans.add(String.valueOf(path));
        }else{
            char[] cs = phone[digits[index] - '2'];
            for (char c : cs) {
                path[index] = c;
                process(digits,index +1,path,ans);
            }
        }


    }

}
