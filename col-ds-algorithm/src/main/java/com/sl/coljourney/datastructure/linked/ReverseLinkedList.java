package com.sl.coljourney.datastructure.linked;

/**
 * 206. 反转链表  难度：简单
 * > 思路：需要两个指针，一个为当前节点指针 curr，一个为其在原链表中的前置节点 before
 * >       每次遍历先保存临时的【下一个】节点，然后将当前节点指向【上一个】节点
 * >       然后将 before 指针指向当前节点 curr
 * >       最后将 curr 指针指向先保存的临时的【下一个】节点
 * > 关键在于这几个指针怎么指向的
 *
 * @author L
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        // 将 curr 指针指向 head
        ListNode curr = head;
        // before 指针为 null
        ListNode before = null;

        while (curr != null) {
            // 先保留当前节点在原链表的下一个节点，防止丢失原链表指针
            ListNode temp = curr.next;
            // 将 curr 指针的 next 指向原链表的上一个节点，实现反转
            curr.next = before;
            // 将 before 指针指向 curr 即当前节点
            before = curr;
            // 将 curr 指针指向原链表的下一个节点
            curr = temp;
        }
        return before;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        ReverseLinkedList reverse = new ReverseLinkedList();
        ListNode listNode1 = reverse.reverseList(listNode);

        System.out.println(listNode1);
    }
}
