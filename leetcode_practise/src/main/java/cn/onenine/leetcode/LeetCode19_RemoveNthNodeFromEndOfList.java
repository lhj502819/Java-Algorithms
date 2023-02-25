package cn.onenine.leetcode;

/**
 * Description：删除链表的倒数第 N 个结点
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/25 14:53
 */
public class LeetCode19_RemoveNthNodeFromEndOfList {

    /**
     * 给定一个链表1->2->3->4->5，移除倒数第N个节点
     * 先移动N个节点，比如 N = 2
     * 定义遍历c = 1、r = 1
     * c那先移动2到了3
     *
     * c和r不断的同时向后移，一直移到末尾，那r的位置就是需要移除的元素了，中间要记录r的prev节点
     * 但是需要考虑删除头节点的情况，删除头节点时，prev为空
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode r = head;
        ListNode prev = null;
        ListNode c = head;
        //
        //n = 2
        //c = 3
        for (int i = 0; i < n; i++) {
            if (c == null) {
                return r;
            }
            c = c.next;
        }
        while (c != null) {
            prev = r;
            r = r.next;
            c = c.next;
        }
        //如果prev == null 表示移除head节点，则直接返回head.next
        if (prev == null ){
            return head.next;
        }
        ListNode next = r.next;
        prev.next = next;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))));
        head.next = next;
        LeetCode19_RemoveNthNodeFromEndOfList leetCode19RemoveNthNodeFromEndOfList = new LeetCode19_RemoveNthNodeFromEndOfList();
        leetCode19RemoveNthNodeFromEndOfList.removeNthFromEnd(head,2);

    }

}
