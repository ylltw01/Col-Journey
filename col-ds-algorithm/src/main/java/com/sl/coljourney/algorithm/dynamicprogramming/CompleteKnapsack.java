package com.sl.coljourney.algorithm.dynamicprogramming;

import java.util.Scanner;

/**
 * https://www.acwing.com/problem/content/3/
 * 3. 完全背包问题
 * 难度：简单
 * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
 * 第 i 种物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 * <p>
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * <p>
 * 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 种物品的体积和价值。
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
 * 10
 * <p>
 * 10 100
 * 5 8
 * 32 47
 * 17 43
 * 7 9
 * 6 4
 * 29 40
 * 2 6
 * 14 31
 * 6 17
 * 1 3
 * <p>
 * 300
 */
public class CompleteKnapsack {

    static class Main {
        public static void main(String[] args) {
            // 读入数据的代码
            int max = scanAndCompute();
            System.out.println(max);

//            int nums = 20;
//            int capcity = 200;
//            int[] goods = {24, 42, 20, 7, 48, 4, 3, 7, 52, 50, 5, 9, 14, 9, 55, 40, 35, 33, 12, 65};
//            int[] prices = {50, 60, 49, 15, 115, 11, 8, 5, 66, 25, 8, 25, 40, 22, 42, 30, 49, 16, 12, 127};

//        int nums = 4;
//        int capcity = 5;
//        int[] goods = {1, 2, 3, 4};
//        int[] prices = {2, 4, 4, 5};
//        int max1 = maxPrices(nums, capcity, goods, prices);
//        System.out.println(max1);
        }

        /**
         * 题解：动态规划
         * f(1) = 2
         * f(2) = max(f(2), f(2-1) + p[1])
         * f(i) = max(f(i), f[i-goods[j] + prices[j])
         * <p>
         * 动态规划还是要根据"三个特征"写出动态转移方程，否则写不出来的
         * 因此得到动态转移方程：
         * dp[i] = Max(dp[i], dp[i - g[j] + p[j] )
         * <p>
         * 因此，先从 dp[1] 开始计算一直计算到 dp[capcity]，这样只要确保从 dp[1] 开始一直是最优解就行
         * 每次计算 dp[1] 都需要使用到所有到元素
         */
        private static int maxPrices(int nums, int capcity, int[] goods, int[] prices) {
            int[] dp = new int[capcity + 1];
            int max = -1;

            for (int i = 1; i <= capcity; i++) {
                for (int j = 0; j < nums; j++) {
                    int preDpIdx = i - goods[j];
                    if (preDpIdx >= 0) {
                        int curr = dp[preDpIdx] + prices[j];
                        dp[i] = Math.max(dp[i], curr);
                        max = Math.max(dp[i], max);
                    }
                }
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
5   8
32  47
17  43
7   9
6   4
29  40
2   6
14  31
6   17
1   3

4 5

1 2
2 4
3 4
4 5
            f(5) =  f(4) + (1,2)        f(3) + (2,4)       f(2) + (3,4)       f(1) + (4,5)


            f(4) = f(1) + f(1) + f(1) + f(1)
            f(4) = f(2) + f(2)
            f(4) = f(3) + f(1)
            f(4) = f(4)

f(1) = 2
f(2) = max(f(2), f(2-1) + p[1])
f(i) = max(f(i), f[i-goods[j] + prices[j])

 */