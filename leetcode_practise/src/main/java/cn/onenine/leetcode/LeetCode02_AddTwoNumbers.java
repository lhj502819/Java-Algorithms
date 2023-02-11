package cn.onenine.leetcode;

/**
 * leetcode 两数相加
 */
public class LeetCode02_AddTwoNumbers {


    /**
     * 重点考虑：
     *  1、链表长度不一致
     *  2、进位的情况
     *  3、节点链接
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode node = null;
        ListNode head = null;
        ListNode prev = null;
        ListNode c1 = l1;
        ListNode c2 = l2;

        while (c1 != null || c2 != null) {
            //如果当前节点为空，则val记为0
            int v1 = c1 != null ? c1.val : 0;
            int v2 = c2 != null ? c2.val : 0;
            //计算两个节点的和
            int sum = v1 + v2 + carry;
            //进位
            carry = sum / 10;
            //当前节点的值
            int nodeVal = sum % 10;
            node = new ListNode(nodeVal);
            if (head == null) {
                head = node;
            }
            if (prev != null){
                prev.next = node;
            }
            prev = node;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }

        if (carry != 0){
            node = new ListNode(carry);
            prev.next = node;
        }

        return head;
    }

    public static void main(String[] args) {

    }

}
