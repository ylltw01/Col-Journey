package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/d1418aaa147a4cb394c3c3efc4302266?tpId=117&&tqId=37844&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * 丢棋子问题
 * 中等  通过率：22.73%  时间限制：1秒  空间限制：256M
 * 知识点
 * 动态规划
 * <p>
 * 一座大楼一共有0~N层，地面算第0层，最高一层为第N层。已知棋子从第0层掉落肯定不会摔碎，从第i层掉落可能回摔碎，也可能不会摔碎(1<=i<=N)。
 * 给定整数N作为楼层数，再给定整数K作为棋子数，返回如果想找到棋子不会摔碎的最高层数，即使在最差的情况下仍的最少次数。一次只能仍一个棋子。
 * <p>
 * 示例1
 * 输入：
 * 10,1
 * 返回值：
 * 10
 * 说明：
 * 因为只有1棵棋子，所以不得不从第1层开始一直试到第10层，在最差的情况下，即第10层是不会摔坏的最高层，最少也要扔10次
 * <p>
 * 示例2
 * 输入：
 * 3,2
 * 返回值：
 * 2
 * 说明：
 * 先在2层扔1棵棋子，如果碎了，试第1层，如果没碎，试第3层
 * <p>
 * 示例3
 * 输入：
 * 105,2
 * 返回值：
 * 14
 * 说明：
 * 第一个棋子先在14层扔，碎了则用仅存的一个棋子试1~13层
 * 若没碎，第一个棋子继续在27层扔，碎了则用仅存的一个棋子试15~26层
 * 若没碎，第一个棋子继续在39层扔，碎了则用仅存的一个棋子试28~38层
 * 若没碎，第一个棋子继续在50层扔，碎了则用仅存的一个棋子试40~49层
 * 若没碎，第一个棋子继续在60层扔，碎了则用仅存的一个棋子试51~59层
 * 若没碎，第一个棋子继续在69层扔，碎了则用仅存的一个棋子试61~68层
 * 若没碎，第一个棋子继续在77层扔，碎了则用仅存的一个棋子试70~76层
 * 若没碎，第一个棋子继续在84层扔，碎了则用仅存的一个棋子试78~83层
 * 若没碎，第一个棋子继续在90层扔，碎了则用仅存的一个棋子试85~89层
 * 若没碎，第一个棋子继续在95层扔，碎了则用仅存的一个棋子试91~94层
 * 若没碎，第一个棋子继续在99层扔，碎了则用仅存的一个棋子试96~98层
 * 若没碎，第一个棋子继续在102层扔，碎了则用仅存的一个棋子试100、101层
 * 若没碎，第一个棋子继续在104层扔，碎了则用仅存的一个棋子试103层
 * 若没碎，第一个棋子继续在105层扔，若到这一步还没碎，那么105便是结果
 * <p>
 * 原题解：https://www.cnblogs.com/willwuss/p/12256475.html
 * 题解：
 * f(0, k) = 0      => 楼层为 0，返回 0
 * f(n, 1) = n      => 在只有一个棋子的情况下，最少仍 n 次
 * <p>
 * 如 n > 1，k > 1，如果从第 i 层开始仍，
 * 如果碎了则，f(n, k) = f(i-1, k-1) + 1
 * 如果没有碎则，f(n, k) = f(n-i, k) + 1
 * <p>
 * 那么：f(n, k) = max(f(i-1, k-1), f(n-i, k)) + 1
 * 由于 i 的取值范围是 1 到 n, 那么步数最少的情况为: f(n, k) = min(min, max(f(i-1, k-1), f(n-i, k))) + 1, 其中 (1<=i<=N))
 */
public class DiuQiZi {

    /**
     * 不明觉厉
     * 状态转移方程:dp[i][j] = dp[i][j-1] + dp[i-1][j-1] + 1
     * 返回最差情况下扔棋子的最小次数
     *
     * @param n int整型 楼层数
     * @param k int整型 棋子数
     * @return int整型
     */
    public int solve(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int h = (int) (Math.log(n) / Math.log(2)) + 1;
        if (k > h) {
            return h;
        }
        int[] dp = new int[k];
        int i = 1;
        while (true) {
            int p = 0;
            for (int j = 0; j < k; j++) {
                int temp = dp[j];
                dp[j] += p + 1;
                p = temp;
                if (dp[j] >= n) {
                    return i;
                }
            }
            i++;
        }
    }

    /**
     * 动态规划：时间复杂度 O(n^2 * k)  内存超限:您的程序使用了超过限制的内存
     * 返回最差情况下扔棋子的最小次数
     *
     * @param n int整型 楼层数
     * @param k int整型 棋子数
     * @return int整型
     */
    public int solve2(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        // 初始化 f(n, 1) 情况
        for (int i = 1; i < n; i++) {
            dp[i][1] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                int min = Integer.MAX_VALUE;
                for (int m = 1; m < i + 1; m++) {
                    min = Math.min(min,
                            Math.max(dp[m - 1][j - 1], dp[i - m][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[n][k];
    }

    /**
     * 暴力破解：时间复杂度 O(n!)   内存超限:您的程序使用了超过限制的内存
     * 返回最差情况下扔棋子的最小次数
     *
     * @param n int整型 楼层数
     * @param k int整型 棋子数
     * @return int整型
     */
    public int solve1(int n, int k) {
        return recursion(n, k);
    }

    private int recursion(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (k == 1) {
            return n;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            min = Math.min(min,
                    Math.max(recursion(i - 1, k - 1), recursion(n - i, k)));
        }
        return min + 1;
    }

}
