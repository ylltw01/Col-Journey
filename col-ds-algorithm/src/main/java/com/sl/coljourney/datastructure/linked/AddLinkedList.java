package com.sl.coljourney.datastructure.linked;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/c56f6c70fb3f4849bc56e33ff2a50b6b?tpId=117&tqId=37814&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 题目描述
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 * <p>
 * 示例1
 * <p>
 * 输入
 * [9,3,7],[6,3]
 * 返回值
 * {1,0,0,0}
 * <p>
 * 解法：
 * 1. 反转链表
 * 2. 借助栈
 */
public class AddLinkedList {

    public ListNode addInList(ListNode head1, ListNode head2) {
        Stack<ListNode> stack1 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1);
            head1 = head1.next;
        }
        Stack<ListNode> stack2 = new Stack<>();
        while (head2 != null) {
            stack2.push(head2);
            head2 = head2.next;
        }

        ListNode rhead = null;
        int last = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode h1 = stack1.pop();
            ListNode h2 = stack2.pop();

            int sum = h1.val + h2.val + last;
            last = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = rhead;
            rhead = node;
        }

        Stack<ListNode> remainStack = stack1.isEmpty() ? stack2 : stack1;

        while (!remainStack.isEmpty()) {
            ListNode h = remainStack.pop();
            int sum = h.val + last;
            last = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = rhead;
            rhead = node;
        }
        if (last != 0) {
            ListNode node = new ListNode(last);
            node.next = rhead;
            rhead = node;
        }
        return rhead;
    }

}
