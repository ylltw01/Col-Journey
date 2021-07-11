package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 * https://leetcode-cn.com/problems/powx-n/
 * 剑指 Offer 16. 数值的整数次方
 * 50. Pow(x, n)
 * 难度：中等
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 */
public class PowxN {

    /**
     * 递归：二分，减少递归次数
     */
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / (x * myPow(x, -n - 1));
        } else if (n == 1) {
            return x;
        } else if (n == 0) {
            return 1;
        } else if (n % 2 == 1) {
            return x * myPow(x, n - 1);
        } else {
//            double ans = myPow(x, n / 2);
//            return ans * ans;
            return myPow(x * x, n / 2);
        }
    }

    public static void main(String[] args) {
        PowxN powxN = new PowxN();
        double v = powxN.myPow(2, -10);
        System.out.println(v);
    }

}
