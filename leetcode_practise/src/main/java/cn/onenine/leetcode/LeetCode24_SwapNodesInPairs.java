package cn.onenine.leetcode;

/**
 * Description：节点交换
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/26 14:24
 */
public class LeetCode24_SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        //将当前节点的prev指向当前节点的next
        //当前节点指向当前节点的prev

        //当前遍历到的节点
        ListNode curNode = head;
        ListNode returnNode = curNode.next;
        //上一个节点
        ListNode prev = null;
        //头节点
        while (curNode != null && curNode.next != null) {
            ListNode next = curNode.next;
            ListNode next_next = curNode.next.next;
            //将next.next执行当前节点
            next.next = curNode;
            //将当前指针next指向next.next.next
            curNode.next = next_next;
            if (prev != null){
                prev.next = next;
            }
            //将当前节点置为prev
            prev = curNode;
            //将index移动到next.next.next
            curNode = next_next;
        }

        return returnNode;
    }

}
