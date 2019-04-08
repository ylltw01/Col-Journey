package com.sl.coljourney.datastructure.linked;

/**
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author L
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode tmpL1 = l1;
        ListNode tmpL2 = l2;
        ListNode curry = listNode;
        int carry = 0;
        while (tmpL1 != null || tmpL2 != null) {
            int x = tmpL1 != null ? tmpL1.val : 0;
            int y = tmpL2 != null ? tmpL2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curry.next = new ListNode(sum % 10);
            curry = curry.next;
            if (tmpL1 != null) {
                tmpL1 = tmpL1.next;
            }
            if (tmpL2 != null) {
                tmpL2 = tmpL2.next;
            }
        }
        if (carry > 0) {
            curry.next = new ListNode(1);
        }
        return listNode.next;
    }

}
