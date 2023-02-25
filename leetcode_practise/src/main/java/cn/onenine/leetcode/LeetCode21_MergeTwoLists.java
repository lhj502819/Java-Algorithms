package cn.onenine.leetcode;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/25 16:08
 */
public class LeetCode21_MergeTwoLists {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
//        head.next = next;

        ListNode head2 = new ListNode(2);
        LeetCode21_MergeTwoLists leetCode21MergeTwoLists = new LeetCode21_MergeTwoLists();
        leetCode21MergeTwoLists.mergeTwoLists(head, head2);

    }

    //124
    //134

    /**
     * 比较两个链表的头节点 a b
     * 如果a < b
         * 那么继续比较 a.next及以后的节点与b链表的大小
         * 返回a
     * 如果a >= b
     *   那么b要在a前边，b.next的值是a和b.next及其以后再比较的结果
     *   返回b
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

}
