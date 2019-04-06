package com.sl.coljourney.datastructure.linked;

/**
 * 19. 删除链表的倒数第N个节点  难度：中等
 * > 解题思路：
 * >   要满足时间复杂度为O(N), 使用两个指针
 * >   1. frist 指针：先走 n 步
 * >   2. second 指针：在和frist指针一样，一步一步向后走
 * >   3. 当 frist 指针的 next 指向空的时候，second 指针则刚刚好在倒数第 N 个节点的前一个节点
 * >   值得注意的两点：
 * >       a：如果 frist 指针在第 1 步的时候先走了 n 步之后 frist 为空，说明删除的是 head 节点，则返回 head.next
 * >       b: 如果最后 second.next 指针为空，则返回空，如: [1], 1 （链表元素为1，删除倒数第 1 个元素）
 *
 * @author L
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode frist = head;
        for (int i = 0; i < n; i++) {
            frist = frist.next;
        }
        if (frist == null) {
            return head.next;
        }
        ListNode second = head;
        while (frist.next != null) {
            frist = frist.next;
            second = second.next;
        }
        if (second.next == null) {
            return null;
        }
        second.next = second.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);

        RemoveNthNodeFromEndOfList remove = new RemoveNthNodeFromEndOfList();
        ListNode result = remove.removeNthFromEnd(listNode, 2);

        System.out.println(result);
    }

}