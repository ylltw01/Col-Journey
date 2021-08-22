package com.sl.coljourney.algorithm.backtracking;

/**
 * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 * 357. 计算各个位数不同的数字个数
 * 难度：中等
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * <p>
 * 示例:
 * <p>
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 * <p>
 * 题解：
 * 实质上就是动态规划
 * 首先手动定义启动项，n = 0 的时候，return 1
 * 当 n = 1 的时候，也就是只有一位数，自然是有 0 - 9 一共10种选择， return 10
 * 当 n = 2 的时候，第一位有 1 - 9 一共 9 种选择，个位数就只有 0 - 9 再去掉前面一位一共 9 种选择，然后再加上 n = 1 时候的总数，也就是 return 9 * 9 + 10
 * 当 n = 3 的时候，第一位还是 1 - 9 一共 9 种，第二位依然是 0 - 9 -1，一共 9种，第三位已经去掉了两位，就是 8 种，所以最后return 9 * 9 * 8 + 9 * 9 + 10
 * 当 n = 4, return 9 * 9 * 8 * 7 + 9 * 9 * 8 + 9 * 9 + 10
 * <p>
 * 作者：jxjxhc-2
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits/solution/dong-tai-gui-hua-la-by-jxjxhc-2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class CountNumbersWithUniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }

        int ans = 10;
        for (int i = 2; i <= n; i++) {
            int sum = 9 * 9;
            int c = i - 2;
            int t = 8;
            while (c >= 1) {
                sum *= t;
                t--;
                c--;
            }
            ans += sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        CountNumbersWithUniqueDigits c = new CountNumbersWithUniqueDigits();
        int i = c.countNumbersWithUniqueDigits(4);
        System.out.println(i);
    }

}
