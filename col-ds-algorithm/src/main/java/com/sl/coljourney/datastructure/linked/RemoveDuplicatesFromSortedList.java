package com.sl.coljourney.datastructure.linked;

/**
 * https://www.nowcoder.com/practice/c087914fae584da886a0091e877f2c79?tpId=117&&tqId=37730&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * 83. 删除排序链表中的重复元素
 * 难度：简单
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode node = head.next;
        ListNode pre = head;
        while (node != null) {
            if (pre.val != node.val) {
                pre.next = node;
                pre = node;
            } else if (node.next == null) {
                pre.next = null;
            }
            node = node.next;
        }
        return head;
    }

    /**
     * 官方精选题解，牛皮
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

}

// [1,1,2,3,3]