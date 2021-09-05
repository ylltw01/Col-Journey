package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/house-robber-ii/
 * 213. 打家劫舍 II
 * 难度：中等
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobber2 {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //          max(选择第一个，不选择最后一个 ,                    不选择第一个，选择最后一个)
        return Math.max(robMax(nums, 0, nums.length - 1), robMax(nums, 1, nums.length));
    }

    public int robMax(int[] nums, int start, int end) {
        int pre3 = 0;
        int pre2 = 0;
        int pre = 0;
        for (int i = start; i < end; i++) {
            int cur = Math.max(pre2, pre3) + nums[i];
            pre3 = pre2;
            pre2 = pre;
            pre = cur;
        }
        return Math.max(pre, pre2);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 2};
        HouseRobber2 robber2 = new HouseRobber2();
        int rob = robber2.rob(arr);
        System.out.println(rob);
    }

}
