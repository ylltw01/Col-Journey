package com.sl.coljourney.algorithm;

/**
 * 9. 回文数  难度：简单
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * @author L
 */
public class PalindromeNumber {

    // 回文数字
    private boolean isPalindrome(int x) {
        // 为0 直接返回 true
        if (x == 0) {
            return true;
        }
        // 如果小于0 或者小于10 大于0 返回 false
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        // 将数字进行反转, 循环跳出条件是 x 大于 rx, 例如：x = 1221 当 rx = 12 时候则退出循环 又例如：x = 12321 只有当 rx = 123 时才退出
        int rx = 0;
        while (x > rx) {
            rx = rx * 10 + x % 10;
            x /= 10;
        }
        // 如上面两个例子, 偶数个数的数字相等, 奇数个数的需要排除个位数字, 因为
        return x == rx || x == rx / 10;
    }

}
