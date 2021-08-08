package com.sl.coljourney.algorithm.backtracking;

/**
 * https://leetcode-cn.com/problems/target-sum/
 * 494. 目标和
 * 难度：中等
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1], target = 1
 * 输出：1
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        return bt(nums, target, 0, 0);
    }

    /**
     * 回溯算法求解：两个路径，当前数为正数；当前数为负数
     */
    private int bt(int[] nums, int target, int start, int sum) {
        if (start == nums.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }
//        这里的代码不是这样写的，因为在后边，还能够用减法减掉的
//        else if (sum > target) {
//            return 0;
//        }
        return bt(nums, target, start + 1, sum + nums[start]) +
                bt(nums, target, start + 1, sum - nums[start]);
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        TargetSum targetSum = new TargetSum();
        int targetSumWays = targetSum.findTargetSumWays(nums, 3);
        System.out.println(targetSumWays);
    }

}
/*
                    1         -1
                1     -1    1    -1

 */
