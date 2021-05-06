package com.sl.coljourney.datastructure.linked;

import java.util.ArrayList;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6?tpId=117&tqId=37747&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * <p>
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 */
public class MergekSortedLists {

    /**
     * 解法：归并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int begin, int end) {
        if (begin == end) {
            return lists[begin];
        }
        int mid = (begin + end) / 2;
        ListNode left = merge(lists, begin, mid);
        ListNode right = merge(lists, mid + 1, end);

        // 这点很妙，避免了很多判断
        ListNode head = new ListNode(-1);
        ListNode current = head;

        while (left != null && right != null) {
            ListNode node;
            if (left.val > right.val) {
                node = right;
                right = right.next;
            } else {
                node = left;
                left = left.next;
            }
            current.next = node;
            current = node;

        }
        current.next = left == null ? right : left;
        return head.next;
    }

    /**
     * 牛客的是 ArrayList
     */
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        return merge(lists, 0, lists.size() - 1);
    }

    public ListNode merge(ArrayList<ListNode> lists, int begin, int end) {
        if (begin == end) {
            return lists.get(begin);
        }
        int mid = (begin + end) / 2;
        ListNode left = merge(lists, begin, mid);
        ListNode right = merge(lists, mid + 1, end);

        // 这点很妙，避免了很多判断
        ListNode head = new ListNode(-1);
        ListNode current = head;

        while (left != null && right != null) {
            ListNode node;
            if (left.val > right.val) {
                node = right;
                right = right.next;
            } else {
                node = left;
                left = left.next;
            }
            current.next = node;
            current = node;

        }
        current.next = left == null ? right : left;
        return head.next;
    }

}
