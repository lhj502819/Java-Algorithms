package cn.onenine.leetcode;

/**
 * Description：两两交换链表中的节点
 * Leetcode 24题 地址 https://leetcode.cn/problems/swap-nodes-in-pairs/
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/12/4 21:57
 */
public class Swap_nodes_in_pairs {
    /**
     * [1,2,3,4]  -> [2,1,4,3]
     * [1,2,3,4,5] -> [2,1,4,3,5]
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode curNode = head;
        ListNode returnNode = curNode.next;
        ListNode preNode = null;
        while (curNode != null && curNode.next != null) {
            ListNode next = curNode.next;
            ListNode nextNext = next.next;
            next.next = curNode;
            curNode.next = nextNext;
            if (preNode != null) {
                preNode.next = next;
            }
            preNode = curNode;
            curNode = nextNext;
        }
        return returnNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))));
        head.next = next;
        ListNode listNode = swapPairs(head);
        System.out.println(listNode);
    }

}



