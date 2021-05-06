package com.sl.coljourney.datastructure.linked;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e?tpId=117&tqId=37746&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * <p>
 * 示例 4：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class ReverseNodesInGroup {

    /**
     * 给定的链表是 1 -> 2 -> 3 -> 4 -> 5
     * 对于 k = 2 , 你应该返回 2 -> 1 -> 4 -> 3 -> 5
     * 对于 k = 3 , 你应该返回 3 -> 2 -> 1 -> 4 -> 5
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();

        ListNode preNode = null;
        ListNode newHead = null;

        while (head != null) {
            int count = 1;
            ListNode startNode = head;
            while (count <= k && head != null) {
                stack.push(head);
                head = head.next;
                count++;
            }
            // 反转
            if (count - 1 < k) {
                if (preNode == null && head == null) {
                    newHead = startNode;
                } else if (preNode == null) {
                    preNode = startNode;
                } else {
                    preNode.next = startNode;
                }
            } else {
                while (!stack.isEmpty()) {
                    ListNode pop = stack.pop();
                    pop.next = null;
                    if (preNode != null) {
                        preNode.next = pop;
                    } else {
                        newHead = pop;
                    }
                    preNode = pop;
                }
            }
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
        ReverseNodesInGroup reverseNodesInGroup = new ReverseNodesInGroup();
        ListNode node = reverseNodesInGroup.reverseKGroup(node1, 2);

        System.out.println(node.val);
    }

}
