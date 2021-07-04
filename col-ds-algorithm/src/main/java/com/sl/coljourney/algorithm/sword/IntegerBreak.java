package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/integer-break/
 * 343. 整数拆分
 * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
 * 剑指 Offer 14- I. 剪绳子
 * <p>
 * 难度：中等
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * <p>
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * <p>
 * 题解：
 * 官方提交的动态规划和数学推导也很精彩
 * <p>
 * https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/
 * 这个题，用二分没通过，没弄清楚为啥
 */
public class IntegerBreak {

    /**
     * 二分法
     */
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int max = Integer.MIN_VALUE;
        // 二分法
        int maxTime = n / 2;
        for (int i = 2; i <= maxTime; i++) {
            int pow = n / i;
            // 如果能被 i 整除，则直接使用 i 的 pow 次方
            if (n % i == 0) {
                max = (int) Math.max(Math.pow(i, pow), max);
            } else {
                // 如果最后一位数值是 1，1 * pow = pow，因此最后一位需要补齐不能用 0
                if (n - i * pow < 2) {
                    pow -= 1;
                }
                int cj = (int) (Math.pow(i, pow) * (n - i * pow));
                max = Math.max(cj, max);
            }
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/
     */
    public int cuttingRope2(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        long result = 1;
        while (n > 4) {
            n -= 3;
            result *= 3;
            result %= 1000000007;
        }
        return (int) (n * result % 1000000007);

//        作者：rain-ru
//        链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/su-kan-shuang-100zhao-gui-lu-by-rain-ru-0yh0/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
//        int i = integerBreak.integerBreak(8);
//        System.out.println(i);
//
//        i = integerBreak.integerBreak(12);
//        System.out.println(i);

        int i = integerBreak.integerBreak(13);
        System.out.println(i);

        i = integerBreak.integerBreak(16);
        System.out.println(i);

        i = integerBreak.integerBreak(28);
        System.out.println(i);

        i = integerBreak.integerBreak(30);
        System.out.println(i);
    }

}
/*
13 = 2 * 2 * 2 * 2 * 2 * 3

 */