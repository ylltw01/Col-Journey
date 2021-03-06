package com.sl.coljourney.datastructure.linked;

/**
 * 876. 链表的中间结点 难度：简单
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 * <p>
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 * @author L
 */
public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        ListNode frist = head;
        ListNode second = head;

        while (second != null) {
            if (second.next == null) {
                break;
            }
            frist = frist.next;
            second = second.next.next;
        }
        return frist;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        ListNode node14 = new ListNode(4);
        ListNode node13 = new ListNode(3);
        ListNode node12 = new ListNode(2);
        ListNode node11 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node14;
        node14.next = node13;
        node13.next = node12;
        node12.next = node11;

        MiddleOfTheLinkedList middle = new MiddleOfTheLinkedList();
        middle.middleNode(node1);
    }

}
