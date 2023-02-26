package cn.onenine.leetcode;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/2/26 13:58
 */
public class LeetCode23_MergeKSortedLists {


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4,new ListNode(5));

        ListNode head2 = new ListNode(1);
        head1.next = new ListNode(3,new ListNode(4));

        ListNode head3 = new ListNode(2);
        head1.next = new ListNode(6);

        ListNode[] listNodes = {head1, head2, head3};
        LeetCode23_MergeKSortedLists leetCode23MergeKSortedLists = new LeetCode23_MergeKSortedLists();
        leetCode23MergeKSortedLists.mergeKLists(listNodes);

    }

    /**
     * 1,4,5
     * 1,3,4
     * 2,6
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0){
            return null;
        }

        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = mergeSort(head,lists[i]);
        }
        return head;
    }

    public ListNode mergeSort(ListNode l1,ListNode l2){
        if (l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }

        if (l1.val< l2.val){
            l1.next = mergeSort(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeSort(l2.next,l1);
            return l2;
        }
    }
}
