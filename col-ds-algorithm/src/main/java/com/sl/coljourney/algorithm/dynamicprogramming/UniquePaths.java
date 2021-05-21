package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/166eaff8439d4cd898e3ba933fbc6358?tpId=117&tqId=37736&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/unique-paths/
 * 62. 不同路径
 * 难度：中等
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * <p>
 * 示例 3：
 * <p>
 * 输入：m = 7, n = 3
 * 输出：28
 * <p>
 * 示例 4：
 * <p>
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * 解法：动态规划
 * 转移方程为：f(i, j) =  f(i - 1, j) + f(i, j - 1)
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        /*
        这个复杂度过高，超时
        if (m == 1 || n == 1) {
            return 1;
        }

        return uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
        */

        int[][] dp = new int[m][n];

        // 初始化第一列：只会有 1 条路径
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 初始化第一行：只会有 1 条路径
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

}
