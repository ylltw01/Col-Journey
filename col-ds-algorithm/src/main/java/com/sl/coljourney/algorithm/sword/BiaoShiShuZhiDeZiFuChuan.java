package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 * 难度：中等
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * <p>
 * 数值（按顺序）可以分成以下几个部分：
 * <p>
 * 1. 若干空格
 * 2. 一个 小数 或者 整数
 * 3.（可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 4. 若干空格
 * <p>
 * 小数（按顺序）可以分成以下几个部分：
 * <p>
 * 1.（可选）一个符号字符（'+' 或 '-'）
 * 2. 下述格式之一：
 * >   2.1. 至少一位数字，后面跟着一个点 '.'
 * >   2.2. 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * >   2.3. 一个点 '.' ，后面跟着至少一位数字
 * <p>
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * 1.（可选）一个符号字符（'+' 或 '-'）
 * 2. 至少一位数字
 * <p>
 * 部分数值列举如下：
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * <p>
 * 部分非数值列举如下：
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "0"
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "e"
 * 输出：false
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "."
 * 输出：false
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "    .1  "
 * 输出：true
 * <p>
 * "-1E-16"
 */
public class BiaoShiShuZhiDeZiFuChuan {

    public boolean isNumber(String s) {
        // 通过判断有没有e，有e则前面为整数或者小数，后面为整数。没有e则为整数或者小数
        s = s.trim();
        if (s.contains("e") && s.indexOf("e") == s.lastIndexOf("e")) {
            String[] ss = s.split("e");
            return ss.length > 1 && checkNumOrSnum(ss[0]) && checkNum(ss[1]);
        }
        if (s.contains("E") && s.indexOf("E") == s.lastIndexOf("E")) {
            String[] ss = s.split("E");
            return ss.length > 1 && checkNumOrSnum(ss[0]) && checkNum(ss[1]);
        }
        return checkNumOrSnum(s);
    }

    /**
     * 判断是否是整数
     */
    private boolean checkNum(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            return s.length() > 1 && isOnlyNum(s.substring(1));
        }
        return isOnlyNum(s);
    }

    /**
     * 判断是否整数或者小数
     */
    private boolean checkNumOrSnum(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (checkNum(s)) {
            return true;
        }
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            return s.length() > 1 && isNumandPoint(s.substring(1));
        }
        return isNumandPoint(s);
    }

    /**
     * 判断是否是合法的小数
     */
    private boolean isNumandPoint(String s) {
        if (s.contains(".") && s.length() > 1) {
            if (s.indexOf(".") != s.lastIndexOf(".")) {
                return false;
            }
            if (s.charAt(0) == '.') {
                return isOnlyNum(s.substring(1));
            }
            if (s.charAt(s.length() - 1) == '.') {
                return isOnlyNum(s.substring(0, s.length() - 1));
            }
            int index = s.indexOf(".");
            return s.length() > 2 && isOnlyNum(s.substring(0, index)) && isOnlyNum(s.substring(index + 1));
        }
        return false;
    }

    /**
     * 判断是否纯数字
     */
    private boolean isOnlyNum(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


}
