package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/coin-lcci/
 * 面试题 08.11. 硬币
 * 难度：中等
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 * <p>
 * 示例1:
 * <p>
 * 输入: n = 5
 * 输出：2
 * 解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * <p>
 * 示例2:
 * <p>
 * 输入: n = 10
 * 输出：4
 * 解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= n (总金额) <= 1000000
 * <p>
 * <p>
 * 题解：这是个典型的背包问题，与完全背包类似
 * 对于 n ，分别使用 1，5，10，25 都有可能组成对应的数量
 * 比如：
 * 在使用硬币 1 的时候：
 * dp[1] = 1
 * dp[5] = 1
 * dp[10] = 1
 * <p>
 * 在使用硬币 5 的时候：
 * dp[1] = 1
 * dp[5] = 2
 * dp[10] = 5 + dp[5] = 3
 * <p>
 * 在使用硬币 10 的时候：
 * dp[1] = 1
 * dp[5] = 2
 * dp[10] = 10 + dp[10] = 4
 * <p>
 * 因此对于每个硬币，dp[i] = dp[i] + dp[i - coin]
 * 其中：dp[i] 可能是之前 coin 计算出来的，因此对于新的 coin 需要加上之前的
 */
public class CoinLcci {

    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        // 这里要注意的是，dp[0] 初始化为 1，作为当 coin = 1 的时候边界值
        dp[0] = 1;

        int[] coins = {1, 5, 10, 25};

        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        CoinLcci coinLcci = new CoinLcci();
        int i = coinLcci.waysToChange(15);
        System.out.println(i);
    }

}
