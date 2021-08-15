package com.sl.coljourney.algorithm.dynamicprogramming;

import java.util.Scanner;

/**
 * https://www.acwing.com/problem/content/4/
 * 4. 多重背包问题 I
 * 难度：简单
 * 有 N 种物品和一个容量是 V 的背包。
 * <p>
 * 第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
 * <p>
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 * 输出最大价值。
 * <p>
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * <p>
 * 接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 * <p>
 * 输出格式
 * 输出一个整数，表示最大价值。
 * <p>
 * 数据范围
 * 0<N,V≤100
 * 0<vi,wi,si≤100
 * 输入样例
 * 4 5
 * 1 2 3
 * 2 4 1
 * 3 4 3
 * 4 5 2
 * 输出样例：
 * 10
 * <p>
 * 题解：
 * https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
 * https://mp.weixin.qq.com/s/M4uHxNVKRKm5HPjkNZBnFA
 * https://zhuanlan.zhihu.com/p/377647595
 */
public class MultipleKnapsack {

    static class Main {

        public static void main(String[] args) {
            // 读入数据的代码
            int max = scanAndCompute();
            System.out.println(max);

        }

        /**
         * 4 5
         * <p>
         * 1 2          3
         * 2 4          1
         * 3 4          3
         * 4 5          2
         * <p>
         * 1 2          1
         * 1 2          1
         * 1 2          1
         * 2 4          1
         * 3 4          1
         * 3 4          1
         * 3 4          1
         * 4 5          1
         * 4 5          1
         */
        private static int maxPrices(int nums, int capcity, int[] nogs, int[] goods, int[] prices) {
            int[] dp = new int[capcity + 1];

            for (int i = 0; i < nums; i++) {
                // 遍历背包容量
                for (int j = capcity; j >= goods[i]; j--) {
                    // 以上为01背包，然后加一个遍历个数
                    // 遍历个数
                    for (int k = 1; k <= nogs[i] && j >= k * goods[i]; k++) {
                        dp[j] = Math.max(dp[j], dp[j - k * goods[i]] + k * prices[i]);
                    }
                }
            }

/*
            for (int i = 0; i < nums; i++) {
// 这里先物品的个数后容量，为啥不行？
                for (int j = 1; j <= nogs[i]; j++) {
                    for (int c = capcity; c >= j * goods[i]; c--) {
                        int preIdx = c - j * goods[i];
                        int curPrices = dp[preIdx] + j * prices[i];
                        dp[c] = Math.max(curPrices, dp[c]);
                    }
//     这里不能顺序的遍历，只能逆序的遍历。因为顺序的遍历会出现重复使用的情况
//     有详细的讲解： https://mp.weixin.qq.com/s/M4uHxNVKRKm5HPjkNZBnFA
//                    for (int c = 0; c <= capcity - j * goods[i]; c++) {
//                        int idx = c + j * goods[i];
//                        int curPrices = dp[c] + j * prices[i];
//                        dp[idx] = Math.max(curPrices, dp[idx]);
//                    }
                }
            }
 */
            return dp[capcity];
        }

        private static int scanAndCompute() {
            Scanner reader = new Scanner(System.in);
            // 物品的数量为N
            int nums = reader.nextInt();
            // 背包的容量为V
            int capcity = reader.nextInt();

            // 每个物品最多多少件
            int[] nogs = new int[nums];
            // 一个长度为N的数组，第i个元素表示第i个物品的体积；
            int[] goods = new int[nums];
            // 一个长度为N的数组，第i个元素表示第i个物品的价值；
            int[] prices = new int[nums];

            for (int i = 0; i < nums; i++) {
                goods[i] = reader.nextInt();
                prices[i] = reader.nextInt();
                nogs[i] = reader.nextInt();
            }
            reader.close();
            return maxPrices(nums, capcity, nogs, goods, prices);
        }
    }


}

/*
30 100
12 15 1
15 18 1
6 9 4
32 43 1
24 12 92
5 5 3
13 26 4
2 3 3
14 9 7
31 28 77
19 18 88
18 17 70
21 23 4
19 24 3
29 37 2
33 34 4
25 32 5
28 47 4
1 1 2
23 25 3
24 30 1
22 24 1
4 7 1
33 40 2
15 29 4
18 9 51
19 17 25
6 10 4
24 20 15
18 23 5


i = 22
j = 1
c = 95
idx = 99
curPrices = 196



 */