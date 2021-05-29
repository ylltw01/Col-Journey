package com.sl.coljourney.algorithm.sword;

/**
 * https://www.nowcoder.com/practice/44d8c152c38f43a1b10e168018dcc13f?tpId=117&tqId=37754&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 * 剑指 Offer 67. 把字符串转换成整数
 * 难度：中等
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * <p>
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * <p>
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * <p>
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 */
public class StringToIntAll {

    /**
     * 正负号
     * 空格
     * 字母
     * 溢出
     */
    public int strToInt(String str) {
        int ans = 0;
        int sign = 1;
        int i = 0;
        // 跳过开始的空格
        for (int j = i; j < str.length(); j++) {
            if (' ' != str.charAt(j)) {
                i = j;
                break;
            }
        }
        if (i >= str.length()) {
            return 0;
        }
        // 处理符号位
        if ('-' == str.charAt(i)) {
            sign = -1;
            i++;
        } else if ('+' == str.charAt(i)) {
            i++;
        }

        for (int j = i; j < str.length(); j++) {
            char cj = str.charAt(j);
            // 处理非数字，直接跳出
            if (cj < '0' || cj > '9') {
                break;
            }
            // cj - '0'  0 的 asscii 码是 48，1  的 asscii 码是 49，减去 '0' 刚刚好得到正数
            int currAns = ans * 10 + (cj - '0');
            // 处理溢出情况，如 main 中测试
            if (currAns / 10 != ans) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = currAns;
        }
        return ans * sign;
    }

    public static void main(String[] args) {
        System.out.println(-123 / 10);
        System.out.println(-123 % 10);

        System.out.println(Integer.MAX_VALUE);
        // 2147483647
        System.out.println(Integer.MAX_VALUE + 1);
        // -2147483648
        System.out.println(Integer.MAX_VALUE + 2);
        // -2147483647

        System.out.println(Integer.MAX_VALUE / 10 == (Integer.MAX_VALUE + 1) / 10);

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Integer.MIN_VALUE - 2);
    }
}
