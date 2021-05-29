package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 70. 爬楼梯
 * 难度：简单
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n == 1|| n ==2) {
            return n;
        }
        int st1 = 1;
        int st2 = 2;

        for (int i = 3; i <=n; i++) {
            int is = st1 + st2;
            st1 = st2;
            st2 = is;
        }
        return st2;
    }

}
