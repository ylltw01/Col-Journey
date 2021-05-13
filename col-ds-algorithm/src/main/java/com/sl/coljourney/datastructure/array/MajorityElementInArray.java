package com.sl.coljourney.datastructure.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=117&tqId=37770&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/submissions/
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 * <p>
 * <p>
 * <p>
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * 示例1
 * 输入
 * 复制
 * [1,2,3,2,2,2,5,4,2]
 * 返回值
 * 复制
 * 2
 * <p>
 * 本题常见的三种解法：
 * <p>
 * 哈希表统计法： 遍历数组 nums ，用 HashMap 统计各数字的数量，即可找出 众数 。此方法时间和空间复杂度均为 O(N)O(N) 。
 * 数组排序法： 将数组 nums 排序，数组中点的元素 一定为众数。
 * 摩尔投票法： 核心理念为 票数正负抵消 。此方法时间和空间复杂度分别为 O(N) 和 O(1)，为本题的最佳解法。
 * <p>
 */
public class MajorityElementInArray {

    /**
     * 摩尔投票法：
     * 1. 假设第一个为众数，投票记为 +1
     * 2. 依次遍历，如果等于假设的众数，则投票 +1，否则 -1
     * 3. 如果能保证输入的数组中一定存在众数，则结果一定大于0
     * 4. 可能不存在的话，则需要再次遍历并统计
     */
    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int vote = 1;
        for (int i = 1; i < nums.length; i++) {
            if (vote == 0) {
                ans = nums[i];
                vote++;
                continue;
            }
            if (nums[i] == ans) {
                vote++;
            } else {
                vote--;
            }
        }

        vote = 0;
        for (int num : nums) {
            if (num == ans) {
                vote++;
            }
        }

        // 当无众数时返回 0
        return vote > nums.length / 2 ? ans : 0;
    }

    public int moreThanHalfNumSolution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxTime = Integer.MIN_VALUE;
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxTime) {
                maxTime = entry.getValue();
                ans = entry.getKey();
            }
        }

        if (maxTime > nums.length / 2) {
            return ans;
        } else {
            return 0;
        }
    }


    // 2,2,1,1,1,2,2
    // 1 2 1 0

    // 1,2,3,2,4,2,5,2,3
}
