package com.sl.coljourney.datastruct.linked;

/**
 * 双向链表：每个节点既保存了下一个节点的引用又保存了上一个节点的引用
 *
 * @author L
 */
public class DoublyLinkedList<E> {

    private Node<E> first;
    private Node<E> last;

    public boolean linkFirst(E val) {
        Node<E> newNode = new Node<E>(null, val, first);
        if (first == null) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        first = newNode;
        return true;
    }

    public boolean linkLast(E val) {
        Node<E> newNode = new Node<E>(last, val, null);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        return true;
    }

    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E value, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.linkFirst(0);
        list.linkLast(1);
        list.linkFirst(-1);
        list.linkLast(2);
        list.linkFirst(-2);
        list.linkFirst(-3);

        System.out.println();

    }

}
