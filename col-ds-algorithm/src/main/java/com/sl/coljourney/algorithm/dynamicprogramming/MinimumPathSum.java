package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/7d21b6be4c6b429bb92d219341c4f8bb?tpId=117&tqId=37823&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 */
public class MinimumPathSum {

    /**
     * 解法：动态规划
     * <p>
     * 状态转移方程：f(i,j) = (i,j) + min(f(i-1,j), f(i,j-1))
     * 增加了一个dp二维数组，避免递归过程重复计算
     * <p>
     * 如果能够直接更新原二维数组，可以降低空间复杂度为O(1)
     */
    public int minPathSum(int[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        return minPath(grid, grid.length - 1, grid[0].length - 1, dp);
    }

    public int minPath(int[][] grid, int i, int j, int[][] dp) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        } else {
            int left = minPath(grid, i, j - 1, dp);
            int up = minPath(grid, i - 1, j, dp);
            int min = grid[i][j] + Math.min(left, up);
            dp[i][j] = min;
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        // f(1,1) = (1,1) + min(f(1, 0), f(0, 1)
        // f(1,0) = (1,0) + min(f(0, 0), f(0, -1))
        // f(0,1) = (0,1) + min(f(-1, 0), f(0, 0)) = 3 + 0

        // f(2,2) = (2,2) + min(f(1, 2), f(2, 1))
        // f(1,2) = (1,2) + min(f(0, 2), f(1, 1))
        // f(2,1) = (2,1) + min(f(1, 1), f(2, 0))

        // f(0,2) = (0,2) + min(f(-1, 2), f(0, 1))
        // f(0,1) = (0,1) + min(f(0, 0), f(-1, 0))

        MinimumPathSum path = new MinimumPathSum();
        int i = path.minPathSum(grid);
        System.out.println(i);
    }

}
