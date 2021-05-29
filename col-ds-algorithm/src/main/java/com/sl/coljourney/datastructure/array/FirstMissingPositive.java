package com.sl.coljourney.datastructure.array;

/**
 * https://www.nowcoder.com/practice/8cc4f31432724b1f88201f7b721aa391?tpId=117&&tqId=37800&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/first-missing-positive/
 * 41. 缺失的第一个正数
 * 难度：困难
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：3
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * <p>
 * 这个还是看官网解析吧
 * <p>
 * >   3  4  -1  1  9  -5
 * 第一步：
 * >   3  4   7  1  9   7
 * 第二步：
 * >  -3  4  -7 -1  9   7
 * 第二步：取值
 * >  1 + 1 = 2
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] arr) {
        // 处理小于等于 0 的, 将小于等于 0 值设置为 arr.length + 1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 0) {
                arr[i] = arr.length + 1;
            }
        }

        // 如果数组的元素的值在 [1, n] 之间，则将数组索引为 arr[i] - 1 的索引的值修改为负数
        for (int i = 0; i < arr.length; ++i) {
            int num = Math.abs(arr[i]);
            if (num <= arr.length) {
                arr[num - 1] = -Math.abs(arr[num - 1]);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            // 如果存在不为负数的数值，说明最小值存在 [1, n] 之间
            if (arr[i] > 0) {
                return i + 1;
            }
        }
        // 否则，最小值就是 arr.length + 1
        return arr.length + 1;
    }

}
