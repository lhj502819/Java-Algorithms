package cn.onenine.leetcode;

/**
 * Description：反转链表
 * leetcode 206题 链接：https://leetcode.cn/problems/reverse-linked-list/description/
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/12/4 21:14
 */
public class Reverse_linked_list {
    /**
     * input [1,2,3,4,5]  output[5,4,3,2,1]
     * 1 -> 2
     * 2 -> 3
     * 3 -> 4
     * 4 -> 5
     * =============
     * head 1
     * head.next=2
     * tempNext = 2.next=3
     * 2.next -> head 1
     *
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curNode = head.next;
        ListNode prev = head;

        head.next = null;

        while (true) {
            ListNode oldNext = curNode.next;
            curNode.next = prev;
            prev = curNode;
            if (oldNext == null)
                break;
            curNode = oldNext;
        }
        return curNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))));
        head.next = next;
        ListNode listNode = reverseList2(head);
        System.out.println(listNode);
    }


    /**
     * 最优解
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curNode = head;
        ListNode prev = null;

        while (curNode != null) {
            ListNode next = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = next;
        }

        return prev;
    }

    /**
     * 投机取巧方式
     */
    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode res = null;
        for (ListNode x = head;x !=null;x=x.next){
            res = new ListNode(x.val,res);
        }
        return res;
    }

}

