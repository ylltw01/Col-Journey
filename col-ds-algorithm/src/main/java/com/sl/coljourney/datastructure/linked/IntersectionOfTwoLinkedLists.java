package com.sl.coljourney.datastructure.linked;

/**
 * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=117&tqId=37761&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如下面的两个链表：
 * 在节点 c1 开始相交。
 * <p>
 * 示例 1：
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 示例 2：
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * <p>
 * 示例 3：
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 解法：
 * 1. 暴力解法
 * 2. 基于Map
 * 3. 巧妙的双指针
 */
public class IntersectionOfTwoLinkedLists {

    /**
     * 双指针解决方案：
     * 解法极为巧妙
     * 创建两个指针 pA 和 pB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     * 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     * 若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
     * <p>
     * 看懂对吧
     * 举个例子：
     * A={1,3,5,7,9,11} 和 B={2,4,9,11}
     * <p>
     * 那么 pA，pB的执行轨迹如下：
     * pA：1,3,5,7, 9,11 2,4,9,11
     * pB：2,4,9,11 1,3, 5,7,9,11
     * 相交的节点9，11
     * 因此，只要两个链表，存在相交的节点，就能相遇
     * <p>
     * 因为 A + B = B + A，因此两个指针要么相遇，要么同时等于null，也不会出现死循环
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pha = headA;
        ListNode phb = headB;

        while (pha != phb) {
            if (pha == null) {
                pha = headB;
            } else {
                pha = pha.next;
            }

            if (phb == null) {
                phb = headA;
            } else {
                phb = phb.next;
            }
        }
        return pha;
    }

    /**
     * 暴力
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        while (headA != null) {
            ListNode nHeadB = headB;
            while (nHeadB != null) {
                if (headA == nHeadB) {
                    return headA;
                } else {
                    nHeadB = nHeadB.next;
                }
            }
            headA = headA.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        l7.next = l8;
//        l8.next = l4;

        IntersectionOfTwoLinkedLists intersection = new IntersectionOfTwoLinkedLists();
        intersection.getIntersectionNode(l1, l7);
    }

}
