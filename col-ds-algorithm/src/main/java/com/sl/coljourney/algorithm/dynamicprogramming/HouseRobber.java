package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/house-robber/
 * 198. 打家劫舍
 * 难度：中等
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * [2,1,1,2]
 * <p>
 * 递归公式：rob = Math.max(pre2, pre3) + num;
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int pre3 = 0;
        int pre2 = 0;
        int pre = 0;
        for (int num : nums) {
            int cur = Math.max(pre2, pre3) + num;
            pre3 = pre2;
            pre2 = pre;
            pre = cur;
        }
        return Math.max(pre, pre2);
    }

    public static void main(String[] args) {
//        int[] arr = {2, 1, 1, 2};
        int[] arr = {2, 7, 9, 3, 1};
        HouseRobber houseRobber = new HouseRobber();
        int rob = houseRobber.rob(arr);
        System.out.println(rob);
    }

}

