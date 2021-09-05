package com.sl.coljourney.algorithm.dynamicprogramming;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/triangle/
 * 120. 三角形最小路径和
 * 难度：中等
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * >    2
 * >   3 4
 * >  6 5 7
 * > 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 示例 2：
 * <p>
 * 输入：triangle = [[-10]]
 * 输出：-10
 * <p>
 * 提示：
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 * <p>
 * 进阶：
 * <p>
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 * <p>
 * >    2
 * >   4 3
 * >  1 5 7
 * > 4 1 8 3
 * <p>
 * 题解：动态规划解法
 * dp[n][m]
 * <p>
 * dp[0][0] = 2
 * dp[1][0] = 6  dp[1][1] = 5
 * dp[2][0] = dp[1][0] + 1 = 7  dp[2][1] = min(dp[1][0], dp[1][1])  + 5 = 10  dp[2][2] = dp[1][1] = 5 + 7 = 12
 * dp[3][0] = dp[2][0] + 4 = 11 dp[3][1] = min(dp[2][0], dp[2][1])  + 1 = 8  dp[3][2] = min(dp[2][1], dp[2][2])  + 8 = 18  dp[3][3] = dp[2][2] + 3 = 15
 * <p>
 * 官方更优解法：https://leetcode-cn.com/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
 */
public class TriangleMinPath {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[n][m];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> level = triangle.get(i);
            int size = level.size();
            for (int j = 0; j < size; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + level.get(j);
                } else if (j == size - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + level.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + level.get(j);
                }
            }
        }

        int ans = dp[n - 1][0];
        for (int s : dp[n - 1]) {
            ans = Math.min(s, ans);
        }
        return ans;
    }
}
/*

 */