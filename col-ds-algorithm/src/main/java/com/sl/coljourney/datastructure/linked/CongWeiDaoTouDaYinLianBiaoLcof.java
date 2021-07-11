package com.sl.coljourney.datastructure.linked;

import com.sl.coljourney.datastructure.linked.ListNode;

/**
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * 剑指 Offer 06. 从尾到头打印链表
 * 难度：简单
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 */
public class CongWeiDaoTouDaYinLianBiaoLcof {

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        ListNode nh = head;
        int count = 0;
        while (nh != null) {
            count++;
            nh = nh.next;
        }

        int[] ans = new int[count];
        int idx = count - 1;
        nh = head;
        while (nh != null) {
            ans[idx] = nh.val;
            nh = nh.next;
            idx--;
        }
        return ans;
    }

}
