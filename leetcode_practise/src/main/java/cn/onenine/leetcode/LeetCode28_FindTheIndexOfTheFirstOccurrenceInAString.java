package cn.onenine.leetcode;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/27 22:33
 */
public class LeetCode28_FindTheIndexOfTheFirstOccurrenceInAString {

    public static void main(String[] args) {
        LeetCode28_FindTheIndexOfTheFirstOccurrenceInAString leetCode27FindTheIndexOfTheFirstOccurrenceInAString = new LeetCode28_FindTheIndexOfTheFirstOccurrenceInAString();
        leetCode27FindTheIndexOfTheFirstOccurrenceInAString.strStr("aaa","aaaa");
    }

    public int strStr(String haystack, String needle) {

        if (haystack == null || needle == null) {
            return 0;
        }


        //sadbsaddutsad  sadd
        char first = needle.charAt(0);
        int done = -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.length() - i  < needle.length()){
                break;
            }
            //每次只要遇到了和needle第一个字符相等的字符，则截取haystack的index+needle.length个元素，判断是否与needle相等
            if (first == haystack.charAt(i) &&
                    haystack.substring(i, i + needle.length()).equals(needle)) {
                done = i;
                break;
            }
        }

        return done;
    }

}
