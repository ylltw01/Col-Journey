package com.sl.coljourney.datastructure.linked;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/3fed228444e740c8be66232ce8b87c2f?tpId=117&tqId=37813&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/palindrome-linked-list/submissions/
 * <p>
 * 234. 回文链表
 * 难度：简单
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * <p>
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();

        ListNode slow = head;
        ListNode fast = head;

        // 偶数个元素的时候，slow 刚刚好在会文的第一个位置
        while (fast != null) {
            // fast.next == null 会出现在奇数个元素到时候
            if (fast.next == null) {
                // 奇数个元素的时候，slow 刚刚好在中间位置，需要下移一个元素；
                slow = slow.next;
                break;
            }
            stack.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        while (!stack.isEmpty() && slow != null) {
            ListNode pop = stack.pop();
            if (pop.val != slow.val) {
                return false;
            }
            slow = slow.next;
        }

        return stack.isEmpty() && slow == null;
    }

    /**
     * 官方题解
     * 1. 找到前半部分链表的尾节点。
     * 2. 反转后半部分链表。
     * 3. 判断是否回文。
     * 4. 恢复链表。
     * 5. 返回结果。
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);

        ListNode node14 = new ListNode(4);
        ListNode node13 = new ListNode(3);
        ListNode node12 = new ListNode(2);
        ListNode node11 = new ListNode(1);

  /*      node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node4.next = node14;

//        node4.next = node5;
//        node5.next = node14;

        node14.next = node13;
        node13.next = node12;
        node12.next = node11;*/

        node1.next = node2;
        node2.next = node12;
        node12.next = node11;

        PalindromeLinkedList palindrome = new PalindromeLinkedList();
        boolean palindrome1 = palindrome.isPalindrome(node1);
        System.out.println(palindrome1);
    }

}

/*
1 2 2 1

1
1

2
2

1 0 1

1
1

0
1

 */