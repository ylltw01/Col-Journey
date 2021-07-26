package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/jump-game/
 * 55. 跳跃游戏
 * 难度：中等
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class JumpGame {

    /**
     * 贪心
     */
    public boolean canJump1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 动态规划
     */
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[dp.length - 1] = true;
        int match = dp.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= match) {
                dp[i] = true;
                match = i;
            } else {
                dp[i] = false;
            }
        }
        return dp[0];
    }

}
/*
[2, 3, 1, 1, 4]

 t  t  t  t  t

[3, 2, 1, 0, 4]
 f  f  f  f  t

[3, 2, 2, 0, 4]
 t  t  t  f  t


 */
