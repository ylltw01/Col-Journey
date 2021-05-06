package com.sl.coljourney.algorithm;

/**
 * https://www.nowcoder.com/practice/0c9664d1554e466aa107d899418e814e?tpId=117&tqId=37833&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/number-of-islands/
 * <p>
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 解法：深度优先
 * 1. 如果 [i,j] 为 1，那么表示存在一个岛
 * 2. 根据 [i,j] 的坐标，进行左、右、上、下的遍历，然后将能到达的坐标都置为 0
 * 3. 退出条件就是，i 和 j 出数组界了或者是  [i,j] 等于 0
 * 4. 遇到下一个 [i,j] 为 1 的继续
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        // 左
        dfs(grid, i, j - 1);
        // 右
        dfs(grid, i, j + 1);
        // 上
        dfs(grid, i + 1, j);
        // 下
        dfs(grid, i - 1, j);
    }

    public static void main(String[] args) {
//        char[][] chars = {
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'},
//        };

        char[][] chars = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'},
        };

        NumberOfIslands islands = new NumberOfIslands();
        int i = islands.numIslands(chars);
        System.out.println(i);
    }

}
