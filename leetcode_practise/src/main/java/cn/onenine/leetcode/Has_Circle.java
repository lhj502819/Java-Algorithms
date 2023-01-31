package cn.onenine.leetcode;

import java.util.*;

/**
 * Description：判断链表中是否有环 leetcode 141题
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2023/1/31 22:39
 */
public class Has_Circle {

    /**
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     */
    public boolean hasCycle1(ListNode head) {

        List<ListNode> l = new ArrayList<>();
        ListNode curr = head;
        while (curr != null){
            l.add(curr);
            if(l.contains(curr.next)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {

        Set<ListNode> l = new HashSet<>();
        ListNode curr = head;
        while (curr != null){
            if(!l.add(curr.next)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }


}
