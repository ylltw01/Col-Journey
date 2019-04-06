package com.sl.coljourney.datastructure.linked;

/**
 * 21. 合并两个有序链表  难度：简单
 * > 两种方式实现：1. 递归 2. 循环
 *
 * @author L
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head;
        if (l1.val > l2.val) {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        } else {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        }
        return head;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return head.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        MergeTwoSortedLists merge = new MergeTwoSortedLists();
//        ListNode listNode = merge.mergeTwoLists(l1, l2);
        ListNode listNode = merge.mergeTwoLists2(l1, l2);

        System.out.println(listNode);
    }

}
