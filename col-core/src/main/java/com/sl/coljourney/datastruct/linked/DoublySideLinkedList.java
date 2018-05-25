package com.sl.coljourney.datastruct.linked;

/**
 * 双端链表，链表中保存了对最后一个节点的引用
 *
 * @author L
 */
public class DoublySideLinkedList {

    private Node first;
    private Node last;

    public boolean linkFirst(Node node) {
        if (first == null) {
            last = node;
        }
        node.next = first;
        first = node;
        return true;
    }

    public boolean linkLast(Node node) {
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
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

        DoublySideLinkedList list = new DoublySideLinkedList();
        list.linkFirst(new Node(0));
        list.linkFirst(new Node(-1));
        list.linkLast(new Node(1));
        list.linkLast(new Node(2));
        list.linkFirst(new Node(-2));

        System.out.println();
    }

}
