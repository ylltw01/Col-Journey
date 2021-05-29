package com.sl.coljourney.algorithm;

/**
 * https://www.nowcoder.com/practice/1a3de8b83d12437aa05694b90e02f47a?tpId=117&&tqId=37755&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/reverse-integer/
 * 7. 整数反转
 * 难度：简单
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * <p>
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 示例 3：
 * <p>
 * 输入：x = 120
 * 输出：21
 * <p>
 * 示例 4：
 * <p>
 * 输入：x = 0
 * 输出：0
 */
public class ReverseInteger {

    public int reverse(int x) {
        int ans = 0;
        int fh = 1;
        if (x < 0) {
            fh = -1;
            x = -x;
        }

        while (x > 0) {
            int cur = x % 10;
            x = x / 10;
            int curAns = ans * 10 + cur;
            if (curAns / 10 != ans) {
                return 0;
            }
            ans = curAns;
        }

        return ans * fh;
    }

}
