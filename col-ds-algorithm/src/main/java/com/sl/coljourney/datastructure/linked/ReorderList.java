package com.sl.coljourney.datastructure.linked;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/3d281dc0b3704347846a110bf561ef6b?tpId=117&&tqId=37712&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/reorder-list/submissions/
 * 143. 重排链表
 * 难度：中等
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * <p>
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 题解：
 * 1. 栈 + 遍历反转 【方法2】
 * 2. 找到中点 -> 反转后半部分 -> 合并两段
 * >  这个解法比较难巧妙的是，后半部分反转然后在合并两段
 * >  在实现的时候注意：
 * >       1. 中点找到是哪里不重要[奇数或偶数]，重要的是能将链表分为两端，即前半段的最后节点的 next 要为 null。不然容易出现死循环
 * >       2. 反转后半段
 * >       3. 合并两段，前 -> 后 -> 前 -> 后， 这样两段要么相等，要么有一段就多一个元素
 */
public class ReorderList {

    /**
     * 官方最优解法
     * 1. 找到中点
     * 2. 反转后半部分
     * 3. 合并两段
     * 1, 2, 3, 4
     * 1 -> 4 -> 2 -> 3
     * <p>
     * 1, 2, 3, 4, 5
     * 1 -> 5 -> 2 -> 4 -> 3
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode middle = slow.next;
        slow.next = null;

        ListNode current = middle;
        ListNode pre = null;

        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }

        ListNode mergeNode = head;
        ListNode tailNode = pre;

        while (tailNode != null && mergeNode != null) {
            ListNode mergetemp = mergeNode.next;
            ListNode tailtemp = tailNode.next;
            mergeNode.next = tailNode;
            tailNode.next = mergetemp;

            mergeNode = mergetemp;
            tailNode = tailtemp;
        }

    }

    /**
     * 栈反转过程 [速度很惨]
     */
    public void reorderList2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        ListNode mnode = head;
        // 1, 2, 3   |  1, 2
        while (mnode != null && !stack.isEmpty()) {
            // 5, 4, 3  | 4 , 3
            ListNode popNode = stack.pop();
            // 2, 3     | 2 , 3
            ListNode temp = mnode.next;
            // 奇数个数
            if (popNode == mnode) {
                mnode.next = null;
                break;
            }
            // 偶数个数
            if (popNode == temp) {
                mnode.next = temp;
                temp.next = null;
                break;
            }
            // 1 -> 5, 2 -> 4   | 1 -> 4
            mnode.next = popNode;
            // 5 -> 2, 4 -> 3   | 4 -> 2
            popNode.next = temp;
            // 2, 3    | 2
            mnode = temp;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ReorderList reorderList = new ReorderList();
        reorderList.reorderList(node1);

        System.out.println(node1);
    }

}
// 1 2 3 4
// 1 4 2 3

// 1, 2, 3, 4, 5
// 1 -> 5 -> 2 -> 4 -> 3

// 1, 2, 3, 4, 5, 6, 7
//1 -> 7 -> 2