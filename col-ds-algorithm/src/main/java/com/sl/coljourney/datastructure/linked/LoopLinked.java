package com.sl.coljourney.datastructure.linked;

/**
 * 单链表问题：
 * <p>
 * 给定一个单链表，实现以下问题
 * -- 问题一：判断单链表中是否存在环状？
 * -- 问题二：如果单链表存在环，那么求环的入口节点？
 * -- 问题三：如果单链表存在环，那么求环的长度？
 * -- 问题四：如果单链表存在环，那么求链表的长度？
 * -- 问题五：如何判断两个无环链表是否相交？
 * -- 问题六：如果两个无环链表相交，求出第一个相交的节点？
 * -- 问题七：求出环上距离任意一个节点最远的点（对面节点）？
 * </p>
 * 实现：
 * -- 方案一：借助Map结构实现，最简单粗暴
 * -- 方案二：使用快慢指针
 * <p>
 * 问题四：如果单链表存在环，那么求链表的长度？
 * -- 链表的长度等于环的长度加上开始点到入口点的长度
 * </p>
 * <p>
 * 问题五：如何判断两个无环链表是否相交？
 * *   问题六：如果两个无环链表相交，求出第一个相交的节点？
 * --  可以将其中一个链表首尾相连，然后在判断另外一个链表是否存在环，如果存在环那么表示两个链表相交。
 * --  相交的点就是环的入口点，也就转化为问题二
 * </p>
 * <p>
 * 问题七：求出环上距离任意一个节点最远的点（对面节点）？
 * -- 同样使用快慢指针；从换上任意一点开始，如：nodeA；slow一次走一步，fast一次走两步
 * -- 当fast = nodeA 或者 fast = nodeA.next的时候，这时slow所在的节点（nodeZ）就是nodeA在环上距离最远的点
 * -- 求证：设环的长度为R，
 * --       当fast = nodeA 或者 fast = nodeA.next的时候；也就是fast指针刚好走完了环的长度R
 * --       由于fast一次走的是slow的两倍，因此，slow刚好走到环的R/2个节点，也就是在环上对面的节点
 * </p>
 *
 * @author L
 */
public class LoopLinked {

    public static void main(String[] args) {

        SingleLinkedList list = initSingleLinked();

        boolean loopLinked = isLoopLinked(list);
        if (loopLinked) {
            System.out.println("该链表中存在环");
            SingleLinkedList.Node node = loopLinkedEnter(list);
            assert node != null;
            System.out.println("该链表中环的入口点为: " + node.data);
            System.out.println("该链表中环的长度为: " + loopLinkedLength(list));
        }
    }

    /**
     * 问题一：给一个单链表，判断单链表中是否存在环状？
     * <p>
     * 通过快慢指针实现：
     * 类似于环形跑道，一个跑的快的，一个跑的慢，在跑了N（N >= 1）后，跑的快的一定追上跑的慢的
     * -- 快指针一次走两步；
     * -- 慢指针一次走一步；
     * -- 当快指针与慢指针岁对应的对象一样的时候，即表示该链表存在环。否则当慢节点或快节点的下一个节点为null时，则表示不存在环
     * </p>
     *
     * @param list 给定的单链表
     * @return true: 存在, false: 不存在
     */
    private static boolean isLoopLinked(SingleLinkedList list) {
        SingleLinkedList.Node slow = list.getHead();
        SingleLinkedList.Node fast = slow;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 问题二：如果一个单链表存在环，那么求环的入口节点？
     * <p>
     * 通过求证计算发现，从开始节点到入口的步数和从快慢指针相遇点到入口的步数一样；
     * 也就是说，一个指针从开始节点一步一步走，另外一个指针从相遇点一步一步走，他们的相遇点为环的入口点
     * (求证过程见md文件)
     * </p>
     *
     * @param list 带环的单链表
     * @return 环的入口点
     */
    private static SingleLinkedList.Node loopLinkedEnter(SingleLinkedList list) {
        SingleLinkedList.Node slow = list.getHead();
        SingleLinkedList.Node fast = slow;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = list.getHead();
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 问题三：如果一个单链表存在环，那么求环的长度？
     * <p>
     * 通过快慢指针判断链表是否存在环；
     * 如果存在环，让其中的一个指针一次走一步，直到再回到相遇点所经过的步数就是环的长度
     * </p>
     *
     * @param list list 带环的单链表
     * @return 链表中环的长度
     */
    private static int loopLinkedLength(SingleLinkedList list) {
        SingleLinkedList.Node slow = list.getHead();
        SingleLinkedList.Node fast = slow;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = slow.next;
                int length = 1;
                while (slow != fast) {
                    slow = slow.next;
                    length++;
                }
                return length;
            }
        }
        return 0;
    }

    /**
     * 创建一个带环的单链表
     *
     * @return 带环单链表SingleLinkedList
     */
    private static SingleLinkedList initSingleLinked() {
        SingleLinkedList list = new SingleLinkedList();
        list.linkLast(new SingleLinkedList.Node(1));
        list.linkLast(new SingleLinkedList.Node(2));
        SingleLinkedList.Node node3 = new SingleLinkedList.Node(3);
        list.linkLast(node3);
        list.linkLast(new SingleLinkedList.Node(4));
        list.linkLast(new SingleLinkedList.Node(5));
        list.linkLast(new SingleLinkedList.Node(6));
        list.linkLast(new SingleLinkedList.Node(7));
        list.linkLast(node3);
        return list;
    }

}
