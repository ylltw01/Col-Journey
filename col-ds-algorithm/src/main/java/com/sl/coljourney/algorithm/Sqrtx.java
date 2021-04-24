package com.sl.coljourney.algorithm;

/**
 * https://leetcode-cn.com/problems/sqrtx/
 * https://www.nowcoder.com/practice/09fbfb16140b40499951f55113f2166c?tpId=117&tqId=37734&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class Sqrtx {

    /**
     * 解法：二分查找
     * 1. 首先使用 （0 + x）/ 2 计算中间值为 mid
     * 2. 当 mid * mid <= x && (mid + 1) * (mid + 1) > x时，返回结果
     * 3. 当 mid * mid < x时，到 mid 的右半部分继续寻找，即 min = mid + 1，范围为 （mid + 1，max）
     * 4. 当 mid * mid > x时，到 mid 的左半部分继续寻找，即 max = mid - 1，范围为 （min，mid -1）
     * 避免溢出使用逆向运算或者使用官方的转为long
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int min = 0;
        int max = x;

        while (true) {
            int mid = (max + min) / 2;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
                return mid;
            } else if (mid > x / mid) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
    }

    public int mySqrt2(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

}
