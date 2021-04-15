package com.sl.coljourney.datastructure.linked;

import javax.swing.*;

/**
 * https://leetcode-cn.com/problems/swapping-nodes-in-a-linked-list/
 * 1721. 交换链表中的节点
 * 给你链表的头节点 head 和一个整数 k 。
 * <p>
 * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * 输出：[7,9,6,6,8,7,3,0,9,5]
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 示例 4：
 * <p>
 * 输入：head = [1,2], k = 1
 * 输出：[2,1]
 * <p>
 * 示例 5：
 * 输入：head = [1,2,3], k = 2
 * 输出：[1,2,3]
 * <p>
 * 解决：
 * 双指针
 */
public class SwappingNodesInLinkedList {

    public ListNode swapNodes(ListNode head, int k) {
        ListNode kNode = head;
        for (int i = 0; i < k - 1; i++) {
            kNode = kNode.next;
        }

        ListNode slowNode = head;
        ListNode fastNode = kNode;
        while (fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }

        int temp = slowNode.val;
        slowNode.val = kNode.val;
        kNode.val = temp;
        return head;
    }

    public static void main(String[] args) {
        // 12345 2
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        head.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        SwappingNodesInLinkedList swap = new SwappingNodesInLinkedList();
        swap.swapNodes(head, 2);

    }
}
