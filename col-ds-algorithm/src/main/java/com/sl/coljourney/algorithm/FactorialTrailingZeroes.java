package com.sl.coljourney.algorithm;

/**
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang-3/
 * 172. 阶乘后的零
 * 难度：简单
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * <p>
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 * <p>
 * 这个还是看官方详细通俗的思路分析解题
 */
public class FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

    public static void main(String[] args) {
        FactorialTrailingZeroes fz = new FactorialTrailingZeroes();
        int i = fz.trailingZeroes(126);
        System.out.println(i);
    }

}

/*

126 / 5 = 25

25 / 5 = 5

5 / 5 = 1

 */
