package com.sl.coljourney.datastruct.linked;

/**
 * 数据结构-单链表
 * 链表中存储头结点（head），每个节点中存储指向下一个节点的应用，这样一节一节向下存储，一直到最后一个节点
 * 每个节点存储：1.存储数据元素，2.存储下一个节点的指针
 *
 * @author L
 */
public class SingleLinkedList {

    private Node head;

    public boolean linkFirst(Node node) {
        Node temp = head;
        head = node;
        node.next = temp;
        return true;
    }

    public boolean linkLast(Node node) {
        Node temp = head;
        if (temp == null) {
            head = node;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        return true;
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.linkLast(new Node(1));
        list.linkLast(new Node(2));
        list.linkLast(new Node(3));
        list.linkFirst(new Node(0));
        list.linkFirst(new Node(-1));
        list.linkFirst(new Node(-2));

        System.out.println();

    }


}
