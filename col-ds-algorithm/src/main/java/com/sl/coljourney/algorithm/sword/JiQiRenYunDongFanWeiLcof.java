package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 * 剑指 Offer 13. 机器人的运动范围
 * 难度：中等
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * <p>
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class JiQiRenYunDongFanWeiLcof {

    public int movingCount(int m, int n, int k) {
        int[][] arr = new int[m][n];
        return moving(m, n, k, 0, 0, arr);
    }

    private int moving(int m, int n, int k, int midx, int nidx, int[][] arr) {
        if (midx < 0 || nidx < 0 || midx >= m || nidx >= n || arr[midx][nidx] == 1) {
            return 0;
        }
        // 这字符串操作，太莽了，又耗时又耗内存
//        int count = 0;
//        String pos = midx + "" + nidx;
//        for (char c : pos.toCharArray()) {
//            count += c - '0';
//        }
        int count = sum(midx) + sum(nidx);
        int ans = 0;
        if (count <= k) {
            ans ++;
            arr[midx][nidx] = 1;
            ans += moving(m, n, k, midx + 1, nidx, arr) +
                    moving(m, n, k, midx, nidx + 1, arr);
        }
        return ans;
    }

    private int sum(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        JiQiRenYunDongFanWeiLcof lcof = new JiQiRenYunDongFanWeiLcof();
        int i = lcof.movingCount(16, 8, 4);
        System.out.println(i);
    }

}
