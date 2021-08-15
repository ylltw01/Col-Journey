package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/robot-in-a-grid-lcci/submissions/
 * 面试题 08.02. 迷路的机器人
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,1,0]
 * ]
 * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释:
 * 输入中标粗的位置即为输出表示的路径，即
 * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
 * 说明：r 和 c 的值均不超过 100。
 */
public class RobotInGridLcci {

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> ans = new ArrayList<>();
        bt(obstacleGrid, 0, 0, ans);
        return ans;
    }

    private boolean bt(int[][] obstacleGrid, int x, int y, List<List<Integer>> ans) {
        if (x >= obstacleGrid.length || y >= obstacleGrid[0].length || obstacleGrid[x][y] != 0) {
            return false;
        }
        // 标记当前节点，避免重复计算
        obstacleGrid[x][y] = 1;
        // 从当前节点开始
        ans.add(Arrays.asList(x, y));

        // 到点
        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
            return true;
        }
        // 向下
        if (bt(obstacleGrid, x + 1, y, ans)) {
            return true;
        }
        // 向右
        if (bt(obstacleGrid, x, y + 1, ans)) {
            return true;
        }
        // 如果当前节点无法走通，则移除
        ans.remove(ans.size() - 1);
        return false;
    }

    public List<List<Integer>> pathWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return new ArrayList<>();
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    if (i - 1 >= 0 && dp[i - 1][j]) {
                        dp[i][j] = true;
                    }
                    if (j - 1 >= 0 && dp[i][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        List<List<Integer>> res = new LinkedList<>();
        if (dp[m - 1][n - 1]) {
            int row = m - 1, col = n - 1;
            while (row != 0 || col != 0) {
                res.add(0, Arrays.asList(row, col));
                if (row - 1 >= 0 && dp[row - 1][col]) {
                    row--;
                } else {
                    col--;
                }
            }
            res.add(0, Arrays.asList(0, 0));
        }
        return res ;
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 0, 1},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };

        RobotInGridLcci robot = new RobotInGridLcci();
        List<List<Integer>> lists = robot.pathWithObstacles2(obstacleGrid);
        System.out.println(lists.size());
    }

}
/*
{0, 0, 0, 0},
{0, 0, 1, 0},
{0, 1, 1, 1},
{0, 1, 0, 0},
{0, 0, 0, 0}

{0, 0, 0, 0},
{0, 0, 1, 0},
{0, 1, 0, 1},
{0, 1, 0, 0},
{0, 0, 0, 0}
 */
