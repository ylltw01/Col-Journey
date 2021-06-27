package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * 剑指 Offer 10- I. 斐波那契数列
 * 难度：简单
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：1
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：5
 */
public class Fibonacci2 {

    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int prePre = 0;
        int pre = 1;

        for (int i = 2; i <= n; i++) {
            // 题中说明需要取模 1000000007
            int cur = (pre + prePre) % 1000000007;
            prePre = pre;
            pre = cur;
        }

        return pre;
    }
}
