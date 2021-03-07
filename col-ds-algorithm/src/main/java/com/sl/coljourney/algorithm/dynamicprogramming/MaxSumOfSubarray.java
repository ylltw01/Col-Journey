package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/554aa508dd5d4fefbf0f86e5fe953abd?tpId=117&tqId=37797&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 * 题目描述
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)，空间复杂度为O(1)
 * <p>
 * 示例1
 * 输入
 * [1, -2, 3, 5, -2, 6, -1]
 * 返回值
 * 12
 * <p>
 * 基于动态规划：
 * 动态规划状态表法，假定数组 dp[i],对于 dp[i] 的最大值等于 max(dp[i - 1] + arr[i], arr[i])
 * 即方程为：dp[i] = max(dp[i - 1] + arr[i], arr[i])
 * 也就是，对于数组中任意一个元素，如果其前面元素的最大值加上其本身，比其本身还要小，那么最大值就需要从其本身开始的子数组开始计算
 * <p>
 * 在状态表数组 dp 中，仅使用了上一个索引的最大值，因此只需要存储一个中间上一个索引的最大值变量即可
 */
public class MaxSumOfSubarray {

    public int maxsumofSubarray(int[] arr) {
        int preMax = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preMax = Math.max(arr[i], arr[i] + preMax);
            max = Math.max(max, preMax);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 5, -2, 6, -1};

        MaxSumOfSubarray solution = new MaxSumOfSubarray();
        int max = solution.maxsumofSubarray(arr);
        System.out.println(max);

    }

}
