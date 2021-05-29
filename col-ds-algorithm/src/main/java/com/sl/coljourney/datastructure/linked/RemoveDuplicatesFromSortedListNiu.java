package com.sl.coljourney.datastructure.linked;

/**
 * https://www.nowcoder.com/practice/71cef9f8b5564579bf7ed93fbe0b2024?tpId=117&tqId=37729&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 删除有序链表中重复出现的元素
 * 难度：中等
 * 题目描述
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为1 2 3 3 4 4 5, 返回1 2 5.
 * 给出的链表为1 1 1 2 3, 返回2 3.
 * <p>
 * 示例1
 * 输入
 * {1,2,2}
 * 返回值
 * {1}
 */
public class RemoveDuplicatesFromSortedListNiu {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode pre = preHead;
        ListNode cur = head;

        while (cur != null) {
            // 如果当前节点与下一个节点相同，则需要循环跳过所有重复的节点
            if (cur.next != null && cur.val == cur.next.val) {
                // 循环跳过
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                // 这里要继续到下一个节点，因为可能会出现下一个节点也是重复到情况，
                // 比如 3 3 3 4 4 这种情况，上面循环完，cur = 3，3 肯定是要删除的，所有将设置为 cur = 4 （第一个4）
                cur = cur.next;
                // 这里要断掉链表的联接，不然就没删掉重复的
                pre.next = null;
            } else {
                // 这里就一直往下推
                pre.next = cur;
                pre = cur;
                cur = cur.next;
            }
        }
        return preHead.next;
    }

}
/*
1 2 2
1 2 3 3 3 4 4 5 , 返回 1 2 5.

1 1 2 3 4 5

 */
