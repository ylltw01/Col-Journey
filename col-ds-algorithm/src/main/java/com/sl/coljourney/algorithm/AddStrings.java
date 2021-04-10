package com.sl.coljourney.algorithm;

/**
 * https://www.nowcoder.com/practice/11ae12e8c6fe48f883cad618c2e81475?tpId=117&tqId=37842&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/add-strings/solution/zi-fu-chuan-xiang-jia-by-leetcode-solution/
 * 415. 字符串相加
 * 难度：简单
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 提示：
 * <p>
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        char[] num1Chars = num1.toCharArray();
        char[] num2Chars = num2.toCharArray();
        StringBuilder sb = new StringBuilder();
        int num1Length = num1Chars.length - 1;
        int num2Length = num2Chars.length - 1;
        int before = 0;
        while (num1Length >= 0 || num2Length >= 0) {
            char c1 = '0';
            if (num1Length >= 0) {
                c1 = num1Chars[num1Length];
            }
            char c2 = '0';
            if (num2Length >= 0) {
                c2 = num2Chars[num2Length];
            }
            int sum = before + Character.getNumericValue(c1) + Character.getNumericValue(c2);
            before = sum / 10;

            sb.append(sum % 10);
            num1Length--;
            num2Length--;
        }

        if (before != 0) {
            return before + sb.reverse().toString();
        } else {
            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        String s = addStrings.addStrings("11", "123");
        System.out.println(s);
    }

}