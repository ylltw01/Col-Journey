package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=117&tqId=37778&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 字符串全排列
 * <p>
 * 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * <p>
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 * <p>
 * 示例1
 * 输入
 * "ab"
 * 返回值
 * ["ab","ba"]
 * <p>
 * 递归公式：f(abc) = a(f(bc)) + b(f(ac)) + c(f(ab))
 */
public class PermutationString {

    public ArrayList<String> permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        reverse(result, "", str);
        return result;
    }

    public void reverse(ArrayList<String> result, String start, String str) {
        if (str.length() == 1) {
            // 字符串全排列，只有在最后一个字符才能确定最终排列的字符串
            String step = start + str;
            if (!result.contains(step)) {
                result.add(step);
            }
            return;
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != 0 && chars[i] == chars[0]) {
                continue;
            }
            reverse(result, start + chars[i], (i - 1 > -1 ? str.substring(0, i) : "") + (i + 1 < str.length() ? str.substring(i + 1) : ""));
        }
    }

    public static void main(String[] args) {
        PermutationString permutationSolution = new PermutationString();
        ArrayList<String> result = permutationSolution.permutation("abc");
        for (String s : result) {
            System.out.println(s);
        }

    }

}
