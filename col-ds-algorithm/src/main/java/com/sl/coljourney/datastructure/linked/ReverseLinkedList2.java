package com.sl.coljourney.datastructure.linked;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c?tpId=117&tqId=37726&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * <p>
 * 92. 反转链表 II
 * 难度：中等
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 */
public class ReverseLinkedList2 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode startPreNode = m > 2 || m == 1 ? null : head;
        ListNode startNode = head;
        int start = 1;
        while (start < m) {
            if (start > 1) {
                startPreNode = startNode;
            }
            startNode = startNode.next;
            start ++;
        }

        // 2  3
        ListNode preNode = startNode;
        // 3  4
        ListNode nextNode = startNode.next;
        while (start < n) {
            // 3  4
            ListNode tempNode = nextNode;
            // 4  5
            nextNode = tempNode.next;
            // 3 -> 2    4 -> 3
            tempNode.next = preNode;
            // 3  4
            preNode = tempNode;

            start ++;
        }

        startNode.next = nextNode;
        if (startPreNode == null) {
            head = preNode;
        } else {
            startPreNode.next = preNode;
        }
        return head;
    }

}
