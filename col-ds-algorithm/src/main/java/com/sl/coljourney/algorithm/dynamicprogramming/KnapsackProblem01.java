package com.sl.coljourney.algorithm.dynamicprogramming;

import java.util.Scanner;

/**
 * https://www.acwing.com/problem/content/description/2/
 * 2. 01背包问题
 * 难度：简单
 * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
 * 第 i 件物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 * <p>
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
 * 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
 * <p>
 * 输出格式
 * 输出一个整数，表示最大价值。
 * <p>
 * 数据范围
 * 0<N,V≤1000
 * 0<vi,wi≤1000
 * <p>
 * 输入样例
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 5
 * 输出样例：
 * 8
 * <p>
 * 输入样例2:
 * 20 200
 * 24 50
 * 42 60
 * 20 49
 * 7 15
 * 48 115
 * 4 11
 * 3 8
 * 7 5
 * 52 66
 * 50 25
 * 5 8
 * 9 25
 * 14 40
 * 9 22
 * 55 42
 * 40 30
 * 35 49
 * 33 16
 * 12 12
 * 65 127
 * 输出样例2：
 * 454
 */
public class KnapsackProblem01 {

    static class Main {

        public static void main(String[] args) {
            // 读入数据的代码
            int max = scanAndCompute();
            System.out.println(max);

//            int nums = 20;
//            int capcity = 200;
//            int[] goods = {24, 42, 20, 7, 48, 4, 3, 7, 52, 50, 5, 9, 14, 9, 55, 40, 35, 33, 12, 65};
//            int[] prices = {50, 60, 49, 15, 115, 11, 8, 5, 66, 25, 8, 25, 40, 22, 42, 30, 49, 16, 12, 127};
//            int max1 = maxPrices(nums, capcity, goods, prices);
//            System.out.println(max1);
        }

        private static int maxPrices(int nums, int capcity, int[] goods, int[] prices) {
            // dp 中，dp[物品数][背包的容量] 值为，当前考察物品放入背包的最大价值，
            // 比如：dp[0][1] = 2 ，表示第 0 个物品放入背包容量为 1，其最大价值为 2
            int[][] dp = new int[nums][capcity + 1];

            // 初始化整个 dp 数组第一行为 -1
            for (int i = 0; i <= capcity; i++) {
                dp[0][i] = -1;
            }
            // 初始化，将第 0 个物品不放进去
            dp[0][0] = 0;
            // 初始化，将第 0 个物品放进去
            dp[0][goods[0]] = prices[0];

            int max = Integer.MIN_VALUE;
            for (int i = 1; i < nums; i++) {
                // 不放第 i 个物品进去，那么当前值为上一行的对应位置的值
                for (int j = 0; j <= capcity; j++) {
                    if (dp[i - 1][j] >= 0) {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

                // 这里可以通过 capcity - goods[i] 减少循环次数，也避免内部判断
                // 放第 i 个物品进去
                for (int j = 0; j <= capcity - goods[i]; j++) {
                    if (dp[i - 1][j] >= 0) {
                        // 这里计算当前位置的值为，当前值为上一行的对应位置的值 + 当前物品的价值
                        int value = dp[i - 1][j] + prices[i];
                        // 用最大值更新，当前物品放入背包后的容量的位置的值
                        if (value > dp[i][j + goods[i]]) {
                            dp[i][j + goods[i]] = value;
                        }
                        max = Math.max(max, value);
                    }
                }
                // 放第 i 个物品进去
        /*      for (int j = 0; j <= capcity; j++) {
                    if (dp[i - 1][j] >= 0) {
                        if (j + goods[i] > capcity) {
                            continue;
                        }
                        int value = dp[i - 1][j] + prices[i];
                        if (value > dp[i][j + goods[i]]) {
                            dp[i][j + goods[i]] = value;
                        }
                        max = Math.max(max, value);
                    }
                }*/
            }
            return max;
        }

        private static int scanAndCompute() {
            Scanner reader = new Scanner(System.in);
            // 物品的数量为N
            int nums = reader.nextInt();
            // 背包的容量为V
            int capcity = reader.nextInt();
            // 一个长度为N的数组，第i个元素表示第i个物品的体积；
            int[] goods = new int[nums + 1];
            // 一个长度为N的数组，第i个元素表示第i个物品的价值；
            int[] prices = new int[nums + 1];

            for (int i = 0; i < nums; i++) {
                // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
                goods[i] = reader.nextInt();
                prices[i] = reader.nextInt();
            }
            reader.close();
            return maxPrices(nums, capcity, goods, prices);
        }
    }


}

/*
4 5

重  价值
1   2
2   4
3   4
4   5
                     第几个，容量，价值
                                0,0,0
               0,0,0                              0,1,2
        1,0,0          1,2,4                  1,2,4              1,3,6
 2,0,0    2,3,4   2,2,4       2,5,8       2,2,4   2,5,8     2,3,6     2,6,10

dp[nums][capcity]

    0   1   2   3   4   5
1   0   2
2   0   2   4   6
3   0   2   4   4       8
4



 */